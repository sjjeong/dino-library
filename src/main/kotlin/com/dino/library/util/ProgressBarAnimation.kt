package com.dino.library.util

import android.view.animation.Animation
import android.view.animation.Transformation
import android.widget.ProgressBar

class ProgressBarAnimation(
    val progressBar: ProgressBar,
    val from: Int,
    val to: Int
) : Animation() {

    init {
        duration = 1000
    }

    override fun applyTransformation(interpolatedTime: Float, t: Transformation?) {
        super.applyTransformation(interpolatedTime, t)
        progressBar.progress = (from + ((to - from) * interpolatedTime).toInt())
    }

}