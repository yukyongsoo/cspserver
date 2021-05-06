package com.yuk.cspserver.storage

import kotlinx.coroutines.flow.toList
import org.springframework.data.r2dbc.core.DatabaseClient
import org.springframework.data.r2dbc.core.awaitOneOrNull
import org.springframework.data.r2dbc.core.flow
import org.springframework.data.r2dbc.query.Criteria.where
import org.springframework.stereotype.Component

@Component
class StorageQueryDAO(private val databaseClient: DatabaseClient) {
    suspend fun findStorages(storageIdList: List<Int>) =
            databaseClient.select().from(StorageReadEntity::class.java)
                    .matching(where("id").`in`(storageIdList))
                    .`as`(StorageReadEntity::class.java)
                    .flow().toList()

    suspend fun getStorage(storageId: Int) =
            databaseClient.select().from(StorageReadEntity::class.java)
                    .matching(where("id").`is`(storageId))
                    .`as`(StorageReadEntity::class.java)
                    .awaitOneOrNull()

    suspend fun getAllStorage() =
            databaseClient.select().from(StorageReadEntity::class.java)
                    .`as`(StorageReadEntity::class.java)
                    .flow().toList()
}