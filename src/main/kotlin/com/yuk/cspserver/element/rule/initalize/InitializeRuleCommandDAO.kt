package com.yuk.cspserver.element.rule.initalize

import org.springframework.data.r2dbc.core.DatabaseClient
import org.springframework.data.r2dbc.core.await
import org.springframework.stereotype.Component

@Component
class InitializeRuleCommandDAO(private val databaseClient: DatabaseClient) {
    suspend fun createInitializeRule(ruleId: Int, archiveId: Int) =
            databaseClient.insert().into(InitializeRuleEntity::class.java)
                    .using(InitializeRuleEntity(ruleId, archiveId))
                    .await()
}