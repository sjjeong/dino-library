package com.dino.library.ui

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import io.reactivex.disposables.CompositeDisposable


abstract class BaseActivity<B : ViewDataBinding>(private val layoutId: Int) : AppCompatActivity() {
    protected lateinit var binding: B

    protected val compositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, layoutId)
        binding.lifecycleOwner = this
    }

    override fun onDestroy() {
        compositeDisposable.clear()
        super.onDestroy()
    }

    fun showKeyboard() {
        (getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager).toggleSoftInput(
            InputMethodManager.SHOW_FORCED,
            0
        )
    }

    fun hideKeyboard() {
        (getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager).hideSoftInputFromWindow(
            (currentFocus
                ?: View(this)).windowToken, 0
        )
    }

}