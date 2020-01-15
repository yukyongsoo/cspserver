package com.yuk.cspserver.element

import org.springframework.data.r2dbc.core.DatabaseClient
import org.springframework.data.r2dbc.core.awaitFirstOrNull
import org.springframework.data.r2dbc.query.Criteria.where
import org.springframework.stereotype.Component

@Component
class ElementQueryDAO(private val databaseClient: DatabaseClient) {
    suspend fun getElement(elementId: String) =
            databaseClient.select().from(ElementReadEntity::class.java)
                    .matching(where("id").`is`(elementId))
                    .`as`(ElementReadEntity::class.java)
                    .awaitFirstOrNull()
}