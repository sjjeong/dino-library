package com.dino.library.ext

import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

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

fun AppCompatActivity.showToast(msg: String, isLong: Boolean = false) {
    Toast.makeText(applicationContext, msg, if (isLong) Toast.LENGTH_LONG else Toast.LENGTH_SHORT)
        .show()
}

fun AppCompatActivity.showToast(msgId: Int, isLong: Boolean = false) {
    showToast(getString(msgId, isLong))
}