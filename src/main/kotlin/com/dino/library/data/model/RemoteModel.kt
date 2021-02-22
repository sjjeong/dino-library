package com.dino.library.data.model

interface RemoteModel<Data> {
    fun toData(): Data
}
