package com.yuk.cspserver.element.file

import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.reactive.asFlow
import org.springframework.data.r2dbc.core.DatabaseClient
import org.springframework.data.r2dbc.query.Criteria.where
import org.springframework.stereotype.Component

@Component
class ElementFileQueryDAO(private val databaseClient: DatabaseClient) {
    suspend fun getElementFile(elementId: String) =
            databaseClient.select().from(ElementFileEntity::class.java)
                    .matching(where("elementId").`is`(elementId))
                    .`as`(ElementFileEntity::class.java)
                    .all().asFlow().toList()
}