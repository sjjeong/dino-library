package com.dino.library.ui

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.annotation.AttrRes
import androidx.annotation.LayoutRes
import androidx.annotation.StyleRes
import androidx.annotation.StyleableRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.LifecycleOwner

abstract class DinoCustomView<B : ViewDataBinding>(
    @LayoutRes private val layoutResId: Int,
    context: Context,
    private val attrs: AttributeSet? = null,
    @AttrRes val defStyleAttr: Int = 0,
    @StyleRes val defStyleRes: Int = 0
) : FrameLayout(
    context,
    attrs,
    defStyleAttr,
    defStyleRes
) {
    protected val binding: B =
        DataBindingUtil.inflate(
            LayoutInflater.from(this.context),
            layoutResId,
            this@DinoCustomView,
            true
        )

    fun withAttrs(@StyleableRes styleableRes: IntArray, attrAction: TypedArray.() -> Unit) {
        context.obtainStyledAttributes(attrs, styleableRes, defStyleAttr, defStyleRes).run {
            attrAction()
            recycle()
        }
    }

    fun setLifecycleOwnerToDataBinding(owner: LifecycleOwner) {
        binding.lifecycleOwner = owner
    }

}