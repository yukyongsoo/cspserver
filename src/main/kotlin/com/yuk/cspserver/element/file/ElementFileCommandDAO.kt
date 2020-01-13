package com.yuk.cspserver.element.file

import com.yuk.cspserver.storage.strategy.StoragePath
import org.springframework.data.r2dbc.core.DatabaseClient
import org.springframework.stereotype.Component

@Component
class ElementFileCommandDAO(private val databaseClient: DatabaseClient) {
    fun saveFile(archiveId: Int, elementId: Int, path: StoragePath) {
        //TODO :: save file path to db
    }

}