package com.yuk.cspserver.type

import org.springframework.data.r2dbc.core.DatabaseClient
import org.springframework.data.r2dbc.core.awaitOne
import org.springframework.data.r2dbc.core.awaitRowsUpdated
import org.springframework.data.r2dbc.query.Criteria.where
import org.springframework.stereotype.Component

@Component
class TypeCommandDAO(private val databaseClient: DatabaseClient) {

    suspend fun addType(name: String, category: TypeCategory): Int {
        val typeEntity = TypeEntity(category.type, name)
        return databaseClient.insert().into(TypeEntity::class.java)
                .using(typeEntity)
                .fetch()
                .awaitOne()["ID"].toString().toInt()
    }

    suspend fun deleteType(typeId: Int) =
            databaseClient.delete().from(TypeEntity::class.java)
                    .matching(where("id").`is`(typeId))
                    .fetch().awaitRowsUpdated()
}