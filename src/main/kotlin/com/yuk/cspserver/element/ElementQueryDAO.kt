package com.yuk.cspserver.element

import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.reactive.asFlow
import org.springframework.data.r2dbc.core.DatabaseClient
import org.springframework.data.r2dbc.core.awaitFirstOrNull
import org.springframework.data.r2dbc.query.Criteria.where
import org.springframework.stereotype.Component

@Component
class ElementQueryDAO(private val databaseClient: DatabaseClient) {
    suspend fun getElement(elementId: Int) =
            databaseClient.select().from(ElementReadEntity::class.java)
                    .matching(where("id").`is`(elementId))
                    .`as`(ElementReadEntity::class.java)
                    .awaitFirstOrNull()

    suspend fun getElementByContentId(contentId: String) =
            databaseClient.select().from(ElementReadEntity::class.java)
                    .matching(where("CONTENT_ID").`is`(contentId))
                    .`as`(ElementReadEntity::class.java)
                    .all().asFlow().toList()
}