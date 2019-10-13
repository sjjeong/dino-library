package com.dino.library.ui

import android.os.Bundle
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.dino.library.ext.showToast
import com.googry.dinolibrary.BR
import org.koin.androidx.viewmodel.ext.android.viewModel


abstract class BaseActivity<B : ViewDataBinding, VM : BaseViewModel>(
    layoutResId: Int,
    viewModelCls: Class<VM>
) : AppCompatActivity(layoutResId) {

    protected val binding by lazy {
        DataBindingUtil.bind<B>(
            (window.decorView
                .findViewById(android.R.id.content) as ViewGroup).getChildAt(0)
        )!!
    }

    protected val viewModel by viewModel(clazz = viewModelCls.kotlin)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding {
            lifecycleOwner = this@BaseActivity
            setVariable(BR.vm, viewModel)
        }
        viewModel {
            liveToast.observe(this@BaseActivity) { this@BaseActivity.showToast(it) }
        }
    }

    protected fun binding(action: B.() -> Unit) {
        binding.run(action)
    }

    protected fun viewModel(action: VM.() -> Unit) {
        viewModel.run(action)
    }

}