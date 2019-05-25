package com.dino.library.data

class DataResource<out T> private constructor(
    val status: Status,
    val data: T?,
    val throwable: Throwable?
) {

    enum class Status {
        SUCCESS,
        ERROR,
        LOADING
    }

    companion object {

        fun <T> success(data: T): DataResource<T> {
            return DataResource(
                Status.SUCCESS,
                data,
                null
            )
        }

        fun <T> error(throwable: Throwable, data: T? = null): DataResource<T> {
            return DataResource(
                Status.ERROR,
                data,
                throwable
            )
        }

        fun <T> loading(data: T? = null): DataResource<T> {
            return DataResource(
                Status.LOADING,
                data,
                null
            )
        }
    }
}
