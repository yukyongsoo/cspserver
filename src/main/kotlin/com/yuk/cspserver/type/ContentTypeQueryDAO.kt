package com.yuk.cspserver.type

import org.springframework.data.r2dbc.core.DatabaseClient
import org.springframework.data.r2dbc.core.awaitFirstOrNull
import org.springframework.data.r2dbc.query.Criteria.where
import org.springframework.stereotype.Component

@Component
class ContentTypeQueryDAO(private val databaseClient: DatabaseClient) {
    suspend fun getTypeByName(contentTypeName: String) =
            databaseClient.select().from(ContentTypeEntity::class.java)
                    .matching(where("name").`is`(contentTypeName))
                    .`as`(ContentTypeEntity::class.java)
                    .awaitFirstOrNull()

    suspend fun getType(contentTypeId: Int) =
            databaseClient.select().from(ContentTypeEntity::class.java)
                    .matching(where("id").`is`(contentTypeId))
                    .`as`(ContentTypeEntity::class.java)
                    .awaitFirstOrNull()
}