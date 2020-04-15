package com.dino.library.ui

import android.animation.ValueAnimator
import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import androidx.core.animation.doOnEnd

class DinoExpandableLayout @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    defStyleRes: Int = 0
) : LinearLayout(context, attrs, defStyleAttr, defStyleRes) {

    private var isInitLayout = false

    private var sectionViewHeight = 0

    private var animator: ValueAnimator? = null

    private var currentState = State.NONE
        set(value) {
            field = value
            changeListeners.forEach { it.onStateChanged(value) }
        }

    private val changeListeners = mutableListOf<OnChangeListener>()

    val isExpanded
        get() = currentState == State.EXPANDED

    init {
        isClickable = true
        orientation = VERTICAL
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        check(childCount == CHILD_COUNT) { "ExpandableLayout must has 2 child view !" }
        if (!isInitLayout) {
            isInitLayout = true

            val sectionView = getChildAt(1)
            sectionViewHeight = sectionView.measuredHeight
            sectionView.layoutParams.height = 0

            currentState = State.COLLAPSED
            super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        }
    }

    override fun performClick(): Boolean {
        toggle()
        return super.performClick()
    }

    private fun toggle() {
        animator?.cancel()
        if (isExpanded) {
            collapse()
        } else {
            expand()
        }
    }

    private fun expand() {
        animate(0, sectionViewHeight)
    }

    private fun collapse() {
        val targetHeight = getChildAt(1).height
        animate(targetHeight, 0)
    }

    private fun animate(start: Int, end: Int) {
        val sectionView = getChildAt(1)

        currentState = if (start > end) State.COLLAPSING else State.EXPANDING
        animator = ValueAnimator.ofInt(start, end).apply {
            addUpdateListener {
                sectionView.layoutParams.height = it.animatedValue as Int
                sectionView.requestLayout()
            }
            doOnEnd {
                currentState = if (start > end) State.COLLAPSED else State.EXPANDED
            }
            duration = DEFAULT_DURATION
            start()
        }
    }

    fun addOnChangeListener(listener: OnChangeListener) {
        changeListeners.add(listener)
    }

    fun removeOnChangeListener(listener: OnChangeListener) {
        changeListeners.remove(listener)
    }

    interface OnChangeListener {
        fun onStateChanged(state: State)
    }

    enum class State {
        NONE,
        EXPANDED,
        COLLAPSED,
        EXPANDING,
        COLLAPSING
    }

    companion object {
        private const val CHILD_COUNT = 2
        private const val DEFAULT_DURATION = 300L
    }

}