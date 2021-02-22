package com.dino.library.data

sealed class DataResource<out T> {
    data class Success<T>(val data: T) : DataResource<T>()
    data class Error(val throwable: Throwable) : DataResource<Nothing>()
    data class Loading<T>(val data: T? = null) : DataResource<T>()

    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success[data=$data]"
            is Error -> "Error[throwable=$throwable]"
            is Loading -> "Loading"
        }
    }

    companion object {
        fun <T> success(data: T) = Success(data)

        fun error(throwable: Throwable) = Error(throwable)

        fun <T> loading(data: T? = null) = Loading(data)
    }
}
