package com.yuk.cspserver.element

import org.springframework.data.r2dbc.core.DatabaseClient
import org.springframework.data.r2dbc.core.awaitFirst
import org.springframework.data.r2dbc.core.awaitRowsUpdated
import org.springframework.data.r2dbc.query.Criteria.where
import org.springframework.stereotype.Component

@Component
class ElementCommandDAO(private val databaseClient: DatabaseClient) {
    suspend fun createElement(name: String, contentId: String, elementTypeId: Int) =
            databaseClient.insert().into(ElementEntity::class.java)
                    .using(ElementEntity(contentId, elementTypeId, name))
                    .fetch().awaitFirst()["id"]

    suspend fun deleteElement(contentId: String, elementId: Int) =
            databaseClient.delete().from(ElementReadEntity::class.java)
                    .matching(where("id").`is`(elementId)
                            .and("CONTENT_ID").`is`(contentId))
                    .fetch().awaitRowsUpdated()
}