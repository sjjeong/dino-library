package com.dino.library.ui

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import io.reactivex.disposables.CompositeDisposable

abstract class BaseFragment<B : ViewDataBinding>(private val layoutId: Int) : Fragment() {

    protected lateinit var binding: B
    protected val compositeDisposable = CompositeDisposable()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, layoutId, container, false)
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onDestroyView() {
        compositeDisposable.clear()
        super.onDestroyView()
    }

    fun showKeyboard() {
        Handler().postDelayed({
            (activity as? BaseActivity<*>)?.showKeyboard()
        }, 50)
    }

    fun hideKeyboard() {
        (activity as? BaseActivity<*>)?.hideKeyboard()
    }
}
