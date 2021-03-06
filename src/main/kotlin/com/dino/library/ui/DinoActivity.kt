package com.dino.library.ui

import android.os.Bundle
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.dino.library.BR
import com.dino.library.ext.showToast
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.lang.reflect.ParameterizedType


@Suppress("UNCHECKED_CAST")
abstract class DinoActivity<B : ViewDataBinding, VM : DinoViewModel>(layoutResId: Int) :
    AppCompatActivity(layoutResId) {

    protected val binding by lazy {
        DataBindingUtil.bind<B>(
            (window.decorView
                .findViewById(android.R.id.content) as ViewGroup).getChildAt(0)
        )!!
    }

    protected open val viewModel by viewModel(
        clazz = ((javaClass.genericSuperclass as ParameterizedType?)
            ?.actualTypeArguments
            ?.get(1) as Class<VM>).kotlin
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding {
            lifecycleOwner = this@DinoActivity
            setVariable(BR.vm, viewModel)
        }
        viewModel {
            liveToast.observe(this@DinoActivity) { this@DinoActivity.showToast(it) }
        }
    }

    protected fun binding(action: B.() -> Unit) {
        binding.run(action)
    }

    protected fun viewModel(action: VM.() -> Unit) {
        viewModel.run(action)
    }

}