package com.dino.library.ext

import androidx.databinding.BindingAdapter
import com.airbnb.lottie.LottieAnimationView
import com.airbnb.lottie.LottieComposition

@BindingAdapter(value = ["lottieUrl", "lottieComposition"], requireAll = false)
fun LottieAnimationView.loadLottie(lottieUrl: String?, lottieComposition: LottieComposition?) {
    lottieComposition?.let {
        setComposition(it)
    } ?: run {
        setAnimationFromUrl(lottieUrl)
    }
}