package com.dino.library.ext

import android.content.Intent
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment

inline fun <reified T> AppCompatActivity.startActivity(
    vararg pairs: Pair<String, Any?>,
) {
    startActivity(Intent(this, T::class.java).apply {
        putExtras(bundleOf(*pairs))
    })
}

fun AppCompatActivity.replaceFragmentInActivity(fragment: Fragment, frameId: Int) {
    supportFragmentManager.transact {
        replace(frameId, fragment)
    }
}

fun AppCompatActivity.addFragmentInActivity(fragment: Fragment, frameId: Int) {
    supportFragmentManager.transact {
        add(frameId, fragment)
    }
}

fun AppCompatActivity.showToast(msg: CharSequence, isLong: Boolean = false) {
    Toast.makeText(
        applicationContext,
        msg,
        if (isLong) Toast.LENGTH_LONG else Toast.LENGTH_SHORT
    )
        .show()
}

fun AppCompatActivity.showToast(msgId: Int, isLong: Boolean = false) {
    showToast(getString(msgId), isLong)
}
