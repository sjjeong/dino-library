package com.dino.library.data

sealed class DataResource<out T> {
    data class Success<T>(val data: T) : DataResource<T>()
    data class Error(val exception: Exception) : DataResource<Nothing>()
    object Loading : DataResource<Nothing>()

    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success[data=$data]"
            is Error -> "Error[exception=$exception]"
            Loading -> "Loading"
        }
    }

    companion object {
        fun <T> success(data: T) = Success(data)

        fun error(exception: Exception) = Error(exception)

        fun loading() = Loading
    }
}