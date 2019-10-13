package com.dino.library.ext

import androidx.fragment.app.Fragment
import com.dino.library.ui.DinoActivity

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
    (activity as? DinoActivity<*, *>)?.showKeyboard()
}

fun Fragment.hideKeyboard() {
    (activity as? DinoActivity<*, *>)?.hideKeyboard()
}

fun Fragment.showToast(msg: CharSequence, isLong: Boolean = false) {
    (activity as? DinoActivity<*, *>)?.showToast(msg, isLong)
}

fun Fragment.showToast(msgId: Int, isLong: Boolean = false) {
    (activity as? DinoActivity<*, *>)?.showToast(msgId, isLong)
}