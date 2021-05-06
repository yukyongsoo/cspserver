package com.yuk.cspserver.element.file

import com.yuk.cspserver.storage.strategy.StoragePath
import org.springframework.data.r2dbc.core.DatabaseClient
import org.springframework.data.r2dbc.core.await
import org.springframework.data.r2dbc.core.awaitRowsUpdated
import org.springframework.data.r2dbc.query.Criteria.where
import org.springframework.stereotype.Component

@Component
class ElementFileCommandDAO(private val databaseClient: DatabaseClient) {
    suspend fun saveFile(archiveId: Int, elementId: Int, storageId: Int, path: StoragePath) =
            databaseClient.insert().into(ElementFileEntity::class.java)
                    .using(ElementFileEntity(elementId, archiveId, storageId, path.storagePath, path.filePath))
                    .await()

    suspend fun deleteElement(elementId: Int) =
            databaseClient.delete().from(ElementFileEntity::class.java)
                    .matching(where("ELEMENT_ID").`is`(elementId))
                    .fetch().awaitRowsUpdated()
}