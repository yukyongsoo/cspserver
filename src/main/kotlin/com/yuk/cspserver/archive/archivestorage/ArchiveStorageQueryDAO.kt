package com.yuk.cspserver.archive.archivestorage

import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.reactive.asFlow
import org.springframework.data.r2dbc.core.DatabaseClient
import org.springframework.data.r2dbc.core.awaitOneOrNull
import org.springframework.data.r2dbc.query.Criteria.where
import org.springframework.stereotype.Component

@Component
class ArchiveStorageQueryDAO(private val databaseClient: DatabaseClient) {
    suspend fun getStorageList(id: Int) =
            databaseClient.select().from(ArchiveStorageEntity::class.java)
                    .`as`(ArchiveStorageDTO::class.java)
                    .all().asFlow().toList()

    suspend fun findByStorageId(storageId: String) =
            databaseClient.select().from(ArchiveStorageEntity::class.java)
                    .matching(where("storageId").`is`(storageId))
                    .`as`(ArchiveStorageEntity::class.java)
                    .awaitOneOrNull()

    suspend fun findByArchiveIdAndStorageId(archiveId: Int, storageId: Int) =
            databaseClient.select().from(ArchiveStorageEntity::class.java)
                    .matching(where("archiveId").`is`(archiveId)
                            .and("storageId").`is`(storageId))
                    .`as`(ArchiveStorageEntity::class.java)
                    .awaitOneOrNull()
}