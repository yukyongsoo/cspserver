package com.yuk.cspserver.archive.common

import kotlinx.coroutines.flow.toList
import org.springframework.data.r2dbc.core.DatabaseClient
import org.springframework.data.r2dbc.core.flow
import org.springframework.stereotype.Component

@Component
class ArchiveQueryDAO(private val databaseClient: DatabaseClient) {
    suspend fun getAllArchive() =
        databaseClient.select().from(ArchiveEntity::class.java)
                .`as`(ArchiveDTO::class.java)
                .flow().toList()
}