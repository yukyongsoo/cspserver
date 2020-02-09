package com.yuk.cspserver.type

import org.springframework.data.r2dbc.core.DatabaseClient
import org.springframework.data.r2dbc.core.awaitOneOrNull
import org.springframework.data.r2dbc.query.Criteria.where
import org.springframework.stereotype.Component

@Component
class TypeQueryDAO(private val databaseClient: DatabaseClient) {
    suspend fun getType(typeId: Int) =
            databaseClient.select().from(TypeEntity::class.java)
                    .matching(where("id").`is`(typeId))
                    .`as`(TypeEntity::class.java)
                    .awaitOneOrNull()
}