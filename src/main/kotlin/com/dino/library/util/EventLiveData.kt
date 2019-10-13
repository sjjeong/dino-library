package com.dino.library.util

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer

class EventLiveData<T> : MutableLiveData<Event<T>>() {

    operator fun plusAssign(value: T) {
        setEventValue(value)
    }

    fun setEventValue(value: T) {
        super.setValue(Event(value))
    }

    fun observe(owner: LifecycleOwner, onResult: (T) -> Unit) {
        super.observe(owner, Observer {
            it.get(onResult)
        })
    }

}
