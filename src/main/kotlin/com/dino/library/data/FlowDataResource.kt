package com.dino.library.data

import com.dino.library.data.model.DataModel
import com.dino.library.data.model.DomainModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

fun <Domain : DomainModel, Data : DataModel<Domain>> flowDataResource(
    localData: Data? = null,
    remoteAction: suspend () -> Data,
): Flow<DataResource<Domain>> =
    flow {
        runCatching {
            emit(DataResource.loading(localData?.toDomain()))
            val data = remoteAction().toDomain()
            emit(DataResource.success(data))
        }.onFailure {
            emit(DataResource.error(it))
        }
    }
