package com.yuk.cspserver.archive

import kotlinx.coroutines.flow.toList
import org.springframework.data.r2dbc.core.DatabaseClient
import org.springframework.data.r2dbc.core.flow
import org.springframework.stereotype.Component

@Component
class ArchiveDAO(private val databaseClient: DatabaseClient) {
    suspend fun getAllArchive(): Map<String, Archive> {
        val archiveList = databaseClient.select().from(ArchiveEntity::class.java)
                .`as`(Archive::class.java)
                .flow().toList()
        return archiveList.associateBy { it.name }
    }
}