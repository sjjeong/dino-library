package com.dino.library.ui

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.annotation.AttrRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.LifecycleOwner

abstract class DinoCustomView<B : ViewDataBinding>(
    context: Context,
    attrs: AttributeSet? = null,
    @AttrRes defStyleAttr: Int = 0
) : FrameLayout(
    context,
    attrs,
    defStyleAttr
) {
    init {
        initView()
        getAttrs(attrs, defStyleAttr)
    }

    private fun getAttrs(attrs: AttributeSet?, defStyle: Int, defStyleRes: Int = 0) {
        setTypeArray(
            context.obtainStyledAttributes(
                attrs,
                getCustomViewStyle(),
                defStyle,
                defStyleRes
            )
        )
    }

    private fun initView() {
        binding = DataBindingUtil.bind(LayoutInflater.from(context)
            .inflate(getLayoutId(), this@DinoCustomView, false).apply {
                addView(this)
            })!!
    }

    abstract fun setTypeArray(typedArray: TypedArray)

    abstract fun getLayoutId(): Int

    abstract fun getCustomViewStyle(): IntArray

    protected lateinit var binding: B

    fun setLifecycleOwnerToDataBinding(owner: LifecycleOwner) {
        binding.lifecycleOwner = owner
    }


}