package com.dino.library.util

import com.dino.library.data.DataResource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

abstract class DataRemoteBoundResource<DomainType, DataType> {

    suspend fun flow(): Flow<DataResource<DomainType>> = flow {
        emit(DataResource.loading())
        try {
            emit(DataResource.success(convertDataToDomain(createRemoteSourceResult())))
        } catch (e: Exception) {
            emit(DataResource.error(e))
        }
    }

    abstract suspend fun createRemoteSourceResult(): DataType

    abstract suspend fun convertDataToDomain(dataType: DataType): DomainType

}