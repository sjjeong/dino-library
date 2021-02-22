package com.dino.library.data.model

interface DataModel<Domain> {
    fun toDomain(): Domain
}
