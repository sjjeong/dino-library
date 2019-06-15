package com.dino.library.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

abstract class BaseViewModel :
    ViewModel() {

    protected val _liveLoading = MutableLiveData<Boolean>(false)
    val liveLoading: LiveData<Boolean> get() = _liveLoading

}