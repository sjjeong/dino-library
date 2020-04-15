package com.dino.library.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.dino.library.BR
import com.dino.library.ext.showToast
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.lang.reflect.ParameterizedType

@Suppress("UNCHECKED_CAST")
abstract class DinoFragment<B : ViewDataBinding, VM : DinoViewModel>(layoutResId: Int) :
    Fragment(layoutResId) {

    protected lateinit var binding: B
        private set

    protected val viewModel by viewModel(
        clazz = ((javaClass.genericSuperclass as ParameterizedType?)
            ?.actualTypeArguments
            ?.get(1) as Class<VM>).kotlin
    )

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
