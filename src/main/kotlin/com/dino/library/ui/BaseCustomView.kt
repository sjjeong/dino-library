package com.dino.library.ui

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.LifecycleOwner

abstract class BaseCustomView<B : ViewDataBinding>
    : FrameLayout {

    constructor(context: Context) : super(context) {
        initView()
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        initView()
        getAttrs(attrs)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        initView()
        getAttrs(attrs, defStyleAttr)
    }

    @SuppressLint("CustomViewStyleable")
    private fun getAttrs(attrs: AttributeSet?) {
        setTypeArray(context.obtainStyledAttributes(attrs, getCustomViewStyle()))
    }

    @SuppressLint("CustomViewStyleable")
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
            .inflate(getLayoutId(), this@BaseCustomView, false).apply {
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