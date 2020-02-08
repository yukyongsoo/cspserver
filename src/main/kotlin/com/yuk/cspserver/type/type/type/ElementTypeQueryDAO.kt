package com.yuk.cspserver.type.type.type

import org.springframework.data.r2dbc.core.DatabaseClient
import org.springframework.data.r2dbc.core.awaitOneOrNull
import org.springframework.data.r2dbc.query.Criteria.where
import org.springframework.stereotype.Component

@Component
class ElementTypeQueryDAO(private val databaseClient: DatabaseClient) {
    suspend fun getType(elementTypeId: Int) =
            databaseClient.select().from(ElementTypeEntity::class.java)
                    .matching(where("id").`is`(elementTypeId))
                    .`as`(ElementTypeEntity::class.java)
                    .awaitOneOrNull()
}