package com.dino.library.ext

import android.content.Context
import android.content.Intent
import android.net.Uri
import org.jetbrains.anko.browse

fun Context.callPhone(phoneNumber: String) {
    val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:$phoneNumber"))
    startActivity(intent)
}

fun Context.showPlayStore() {
    browse("market://details?id=$packageName")
}