package com.dino.library.ext

import androidx.fragment.app.Fragment
import com.dino.library.ui.BaseActivity

fun Fragment.replaceFragmentInFragment(fragment: Fragment, frameId: Int) {
    childFragmentManager.transact {
        replace(frameId, fragment)
    }
}

fun Fragment.addFragmentInFragment(fragment: Fragment, frameId: Int) {
    childFragmentManager.transact {
        add(frameId, fragment)
    }
}

fun Fragment.showKeyboard() {
    (activity as? BaseActivity<*>)?.showKeyboard()
}

fun Fragment.hideKeyboard() {
    (activity as? BaseActivity<*>)?.hideKeyboard()
}

fun Fragment.showToast(msg: CharSequence, isLong: Boolean = false) {
    (activity as? BaseActivity<*>)?.showToast(msg, isLong)
}

fun Fragment.showToast(msgId: Int, isLong: Boolean = false) {
    (activity as? BaseActivity<*>)?.showToast(msgId, isLong)
}