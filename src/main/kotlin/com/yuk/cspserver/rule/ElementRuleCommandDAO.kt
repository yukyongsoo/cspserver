package com.yuk.cspserver.rule

import org.springframework.data.r2dbc.core.DatabaseClient
import org.springframework.data.r2dbc.core.awaitFirstOrNull
import org.springframework.stereotype.Component

@Component
class ElementRuleCommandDAO(private val databaseClient: DatabaseClient) {
    suspend fun createRule(elementTypeId : Int, ruleType : Int) =
            databaseClient.insert().into(ElementRuleEntity::class.java)
                    .using(ElementRuleEntity(elementTypeId, ruleType))
                .fetch()
                .awaitFirstOrNull()?.get("id") as Int
}