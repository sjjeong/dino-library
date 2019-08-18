package com.dino.library.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dino.library.util.Event

abstract class BaseViewModel : ViewModel() {

    protected val _liveLoading = MutableLiveData(false)
    val liveLoading: LiveData<Boolean> get() = _liveLoading

    protected val _liveToast = MutableLiveData<Event<CharSequence>>()
    val liveToast: LiveData<Event<CharSequence>> get() = _liveToast


}