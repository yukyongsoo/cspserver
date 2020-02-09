package com.yuk.cspserver.rule

import org.springframework.data.r2dbc.core.DatabaseClient
import org.springframework.data.r2dbc.core.awaitFirstOrNull
import org.springframework.stereotype.Component

@Component
class RuleCommandDAO(private val databaseClient: DatabaseClient) {
    suspend fun createRule(elementTypeId: Int, ruleType: Int) =
            databaseClient.insert().into(RuleEntity::class.java)
                    .using(RuleEntity(elementTypeId, ruleType))
                    .fetch()
                    .awaitFirstOrNull()?.get("id") as Int
}