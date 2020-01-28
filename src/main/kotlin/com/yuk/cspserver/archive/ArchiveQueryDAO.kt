package com.yuk.cspserver.archive

import kotlinx.coroutines.flow.toList
import org.springframework.data.r2dbc.core.DatabaseClient
import org.springframework.data.r2dbc.core.awaitOneOrNull
import org.springframework.data.r2dbc.core.flow
import org.springframework.data.r2dbc.query.Criteria.where
import org.springframework.stereotype.Component

@Component
class ArchiveQueryDAO(private val databaseClient: DatabaseClient) {
    suspend fun getAllArchive() =
            databaseClient.select().from(ArchiveReadEntity::class.java)
                    .`as`(ArchiveDTO::class.java)
                    .flow().toList()

    suspend fun getArchive(archiveId: Int) =
            databaseClient.select().from(ArchiveReadEntity::class.java)
                    .matching(where("id").`is`(archiveId))
                    .`as`(ArchiveDTO::class.java)
                    .awaitOneOrNull()

    suspend fun findByName(name: String) =
            databaseClient.select().from(ArchiveReadEntity::class.java)
                    .matching(where("name").`is`(name))
                    .`as`(ArchiveDTO::class.java)
                    .awaitOneOrNull()
}