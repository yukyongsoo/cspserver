package com.yuk.cspserver.type

import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.reactive.asFlow
import org.springframework.data.r2dbc.core.DatabaseClient
import org.springframework.data.r2dbc.core.awaitOneOrNull
import org.springframework.data.r2dbc.query.Criteria.where
import org.springframework.stereotype.Component

@Component
class TypeQueryDAO(private val databaseClient: DatabaseClient) {
    suspend fun getType(typeId: Int) =
            databaseClient.select().from(TypeReadEntity::class.java)
                    .matching(where("id").`is`(typeId))
                    .`as`(TypeReadEntity::class.java)
                    .awaitOneOrNull()

    suspend fun getAllType() =
            databaseClient.select().from(TypeReadEntity::class.java)
                    .`as`(TypeReadEntity::class.java)
                    .all().asFlow().toList()

    suspend fun findByName(name: String) =
            databaseClient.select().from(TypeReadEntity::class.java)
                    .matching(where("name").`is`(name))
                    .`as`(TypeReadEntity::class.java)
                    .awaitOneOrNull()
}