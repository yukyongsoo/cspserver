package com.yuk.cspserver.storage

import org.springframework.data.r2dbc.core.DatabaseClient
import org.springframework.data.r2dbc.core.await
import org.springframework.data.r2dbc.core.awaitRowsUpdated
import org.springframework.data.r2dbc.query.Criteria.where
import org.springframework.stereotype.Component

@Component
class StorageCommandDAO(private val databaseClient: DatabaseClient) {
    suspend fun addStorage(name: String, path: String, type: StorageType) {
        val storageEntity = StorageEntity(name,true,type.type,path)
        databaseClient.insert().into(StorageEntity::class.java)
                .using(storageEntity)
                .await()
    }

    suspend fun deleteStorage(storageId: String) {
        databaseClient.delete().from(StorageEntity::class.java)
                .matching(where("id").`is`(storageId))
                .fetch().awaitRowsUpdated()
    }
}