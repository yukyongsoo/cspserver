package com.yuk.cspserver.content

import org.springframework.data.r2dbc.core.DatabaseClient
import org.springframework.data.r2dbc.core.awaitOneOrNull
import org.springframework.data.r2dbc.query.Criteria.where
import org.springframework.stereotype.Component

@Component
class ContentQueryDAO(private val databaseClient: DatabaseClient) {
    suspend fun getContent(contentId: String) =
        databaseClient.select().from(ContentEntity::class.java)
                .matching(where("id").`is`(contentId))
                .`as`(ContentEntity::class.java)
                .awaitOneOrNull()
}