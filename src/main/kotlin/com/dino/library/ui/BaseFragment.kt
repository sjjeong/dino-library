package com.dino.library.ui

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.dino.library.ext.showToast
import com.googry.dinolibrary.BR
import org.koin.androidx.viewmodel.ext.android.viewModel

abstract class BaseFragment<B : ViewDataBinding, VM : BaseViewModel>(
    layoutResId: Int,
    viewModelCls: Class<VM>
) : Fragment(layoutResId) {

    protected val binding by lazy { DataBindingUtil.bind<B>(view!!)!! }

    protected val viewModel by viewModel(clazz = viewModelCls.kotlin)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding {
            lifecycleOwner = viewLifecycleOwner
            setVariable(BR.vm, viewModel)
        }
        viewModel {
            liveToast.observe(viewLifecycleOwner) { this@BaseFragment.showToast(it) }
        }
    }

    protected fun binding(action: B.() -> Unit) {
        binding.run(action)
    }

    protected fun viewModel(action: VM.() -> Unit) {
        viewModel.run(action)
    }

}
