package com.yuk.cspserver.archive.archivestorage

import org.springframework.data.r2dbc.core.DatabaseClient
import org.springframework.data.r2dbc.core.await
import org.springframework.data.r2dbc.core.awaitRowsUpdated
import org.springframework.data.r2dbc.query.Criteria.where
import org.springframework.stereotype.Component

@Component
class ArchiveStorageCommandDAO(private val databaseClient: DatabaseClient) {
    suspend fun addArchiveStorage(archiveId: Int, storageId: Int) {
        val archiveStorageEntity = ArchiveStorageEntity(archiveId, storageId)
        databaseClient.insert().into(ArchiveStorageEntity::class.java)
                .using(archiveStorageEntity)
                .await()
    }

    suspend fun deleteArchiveStorage(archiveId: Int, storageId: Int) {
        databaseClient.delete().from(ArchiveStorageEntity::class.java)
                .matching(where("archiveId").`is`(archiveId)
                        .and("storageId").`is`(storageId))
                .fetch().awaitRowsUpdated()
    }
}