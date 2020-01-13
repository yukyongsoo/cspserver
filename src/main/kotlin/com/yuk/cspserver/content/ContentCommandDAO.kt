package com.yuk.cspserver.content

import org.springframework.data.r2dbc.core.DatabaseClient
import org.springframework.data.r2dbc.core.await
import org.springframework.data.r2dbc.core.awaitFirst
import org.springframework.stereotype.Component

@Component
class ContentCommandDAO(private val databaseClient: DatabaseClient) {
    suspend fun createContent(contentId: String, contentTypeId: Int) {
        val entity = ContentEntity(contentId, contentTypeId)
        databaseClient.insert().into(ContentEntity::class.java)
                .using(entity)
                .fetch().awaitFirst()
    }
}