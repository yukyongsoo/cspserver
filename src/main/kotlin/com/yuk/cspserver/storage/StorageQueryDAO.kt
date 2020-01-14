package com.yuk.cspserver.storage

import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.reactive.asFlow
import org.springframework.data.r2dbc.core.DatabaseClient
import org.springframework.data.r2dbc.query.Criteria.where
import org.springframework.stereotype.Component

@Component
class StorageQueryDAO(private val databaseClient: DatabaseClient) {
    suspend fun findStorages(storageIdList: List<Int>) =
            databaseClient.select().from(StorageEntity::class.java)
                    .matching(where("id").`in`(storageIdList))
                    .`as`(StorageEntity::class.java)
                    .all().asFlow().toList()
}