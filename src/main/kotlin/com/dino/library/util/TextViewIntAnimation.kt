package com.dino.library.util

import android.view.animation.Animation
import android.view.animation.Transformation
import android.widget.TextView
import java.text.DecimalFormat


class TextViewIntAnimation(
    val textView: TextView,
    val from: Int = 0,
    val to: Int
) : Animation() {

    init {
        duration = 1000
    }

    private val integerCommaFormat = DecimalFormat("#,###")

    override fun applyTransformation(interpolatedTime: Float, t: Transformation?) {
        super.applyTransformation(interpolatedTime, t)
        textView.text = integerCommaFormat.format((from + ((to - from) * interpolatedTime).toInt()))
    }

}