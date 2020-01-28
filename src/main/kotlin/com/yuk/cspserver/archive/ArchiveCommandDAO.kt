package com.yuk.cspserver.archive

import org.springframework.data.r2dbc.core.DatabaseClient
import org.springframework.data.r2dbc.core.await
import org.springframework.data.r2dbc.core.awaitRowsUpdated
import org.springframework.data.r2dbc.query.Criteria.where
import org.springframework.stereotype.Component

@Component
class ArchiveCommandDAO(private val databaseClient: DatabaseClient) {
    suspend fun deleteArchive(archiveId: Int) =
            databaseClient.delete().from(ArchiveReadEntity::class.java)
                    .matching(where("id").`is`(archiveId))
                    .fetch().awaitRowsUpdated()

    suspend fun addArchive(name: String) {
        val archiveEntity = ArchiveEntity(name)
        databaseClient.insert().into(ArchiveEntity::class.java)
                .using(archiveEntity)
                .await()
    }
}