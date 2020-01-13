package com.yuk.cspserver.archive.archivestorage

import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.reactive.asFlow
import org.springframework.data.r2dbc.core.DatabaseClient
import org.springframework.stereotype.Component

@Component
class ArchiveStorageQueryDAO(private val databaseClient: DatabaseClient) {
    suspend fun getStorageList(id: Int) =
        databaseClient.select().from(ArchiveStorageEntity::class.java)
                .`as`(ArchiveStorageDTO::class.java)
                .all().asFlow().toList()
}