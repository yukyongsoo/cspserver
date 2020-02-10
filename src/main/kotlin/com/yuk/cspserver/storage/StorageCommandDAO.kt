package com.yuk.cspserver.storage

import org.springframework.data.r2dbc.core.DatabaseClient
import org.springframework.data.r2dbc.core.awaitOne
import org.springframework.data.r2dbc.core.awaitRowsUpdated
import org.springframework.data.r2dbc.query.Criteria.where
import org.springframework.stereotype.Component

@Component
class StorageCommandDAO(private val databaseClient: DatabaseClient) {
    suspend fun addStorage(name: String, path: String, type: StorageType): Int {
        val storageEntity = StorageEntity(name, true, type.type, path)
        return databaseClient.insert().into(StorageEntity::class.java)
                .using(storageEntity)
                .fetch()
                .awaitOne()["ID"].toString().toInt()
    }

    suspend fun deleteStorage(storageId: String) {
        databaseClient.delete().from(StorageEntity::class.java)
                .matching(where("id").`is`(storageId))
                .fetch().awaitRowsUpdated()
    }
}