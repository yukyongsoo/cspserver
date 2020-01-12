package com.yuk.cspserver.element.rule

import org.springframework.data.r2dbc.core.DatabaseClient
import org.springframework.data.r2dbc.core.await
import org.springframework.stereotype.Component

@Component
class ElementRuleCommandDAO(private val databaseClient: DatabaseClient) {
    suspend fun createRule(elementTypeId : Int, ruleType : Int) {
        databaseClient.insert().into(ElementRuleEntity::class.java)
                .using(ElementRuleEntity(elementTypeId,ruleType))
                .await()
    }
}