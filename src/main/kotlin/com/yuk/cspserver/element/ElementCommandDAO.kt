package com.yuk.cspserver.element

import org.springframework.data.r2dbc.core.DatabaseClient
import org.springframework.data.r2dbc.core.await
import org.springframework.data.r2dbc.core.awaitFirst
import org.springframework.stereotype.Component

@Component
class ElementCommandDAO(private val databaseClient: DatabaseClient) {
    suspend fun createElement(name: String, contentId: String, elementTypeId: Int) =
        databaseClient.insert().into(ElementEntity::class.java)
                .using(ElementEntity(contentId,elementTypeId,name))
                .fetch().awaitFirst()["id"]
}