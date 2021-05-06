package com.yuk.cspserver.rule.initalize

import org.springframework.data.r2dbc.core.DatabaseClient
import org.springframework.data.r2dbc.core.await
import org.springframework.data.r2dbc.core.awaitRowsUpdated
import org.springframework.data.r2dbc.query.Criteria.where
import org.springframework.stereotype.Component

@Component
class InitializeRuleCommandDAO(private val databaseClient: DatabaseClient) {
    suspend fun createInitializeRule(ruleId: Int, archiveId: Int) =
            databaseClient.insert().into(InitializeRuleEntity::class.java)
                    .using(InitializeRuleEntity(ruleId, archiveId))
                    .await()

    suspend fun deleteInitializeRule(ruleId: Int) =
            databaseClient.delete().from(InitializeRuleEntity::class.java)
                    .matching(where("rule_id").`is`(ruleId))
                    .fetch()
                    .awaitRowsUpdated()
}