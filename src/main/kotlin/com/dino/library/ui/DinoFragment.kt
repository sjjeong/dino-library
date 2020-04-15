package com.dino.library.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.dino.library.ext.showToast
import com.googry.dinolibrary.BR
import org.koin.androidx.viewmodel.ext.android.viewModel

abstract class DinoFragment<B : ViewDataBinding, VM : DinoViewModel>(
    layoutResId: Int,
    viewModelCls: Class<VM>
) : Fragment(layoutResId) {

    protected lateinit var binding: B
        private set

    protected val viewModel by viewModel(clazz = viewModelCls.kotlin)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = super.onCreateView(inflater, container, savedInstanceState)
        binding = DataBindingUtil.bind(view!!)!!
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding {
            lifecycleOwner = viewLifecycleOwner
            setVariable(BR.vm, viewModel)
        }
        viewModel {
            liveToast.observe(viewLifecycleOwner) { this@DinoFragment.showToast(it) }
        }
    }

    protected fun binding(action: B.() -> Unit) {
        binding.run(action)
    }

    protected fun viewModel(action: VM.() -> Unit) {
        viewModel.run(action)
    }

}
