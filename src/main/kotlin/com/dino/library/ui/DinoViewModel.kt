package com.dino.library.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dino.library.util.EventLiveData

abstract class DinoViewModel : ViewModel() {

    private val _liveLoading = MutableLiveData(false)
    val liveLoading: LiveData<Boolean> get() = _liveLoading

    val liveToast = EventLiveData<CharSequence>()

    fun showLoading() {
        _liveLoading.value = true
    }

    fun hideLoading() {
        _liveLoading.value = false
    }

    fun showToast(message: CharSequence) {
        liveToast.setEventValue(message)
    }


}