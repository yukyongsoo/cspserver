package com.yuk.cspserver.content

import org.springframework.data.r2dbc.core.DatabaseClient
import org.springframework.data.r2dbc.core.awaitFirst
import org.springframework.data.r2dbc.core.awaitRowsUpdated
import org.springframework.data.r2dbc.query.Criteria.where
import org.springframework.stereotype.Component

@Component
class ContentCommandDAO(private val databaseClient: DatabaseClient) {
    suspend fun createContent(contentId: String, contentRequest: ContentRequestDTO) {
        val entity = ContentEntity(contentId, contentRequest.name, contentRequest.contentTypeId)
        databaseClient.insert().into(ContentEntity::class.java)
                .using(entity)
                .fetch().awaitFirst()
    }

    suspend fun deleteContent(contentId: String) =
            databaseClient.delete().from(ContentEntity::class.java)
                    .matching(where("id").`is`(contentId))
                    .fetch().awaitRowsUpdated()
}