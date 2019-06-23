package com.dino.library.util

open class Event<out T>(private val content: T) {

    var hasBeenHandled = false
        private set // Allow external read but not write

    /**
     * Returns the content and prevents its use again.
     */
    fun getContentIfNotHandled(): T? {
        return if (hasBeenHandled) {
            null
        } else {
            hasBeenHandled = true
            content
        }
    }

    fun get(action: (T) -> Unit) {
        getContentIfNotHandled()?.let(action)
    }

    /**
     * Returns the content, even if it's already been handled.
     */
    fun peekContent(): T = content
}