package com.yuk.cspserver.rule.initalize

import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.reactive.asFlow
import org.springframework.data.r2dbc.core.DatabaseClient
import org.springframework.data.r2dbc.query.Criteria.where
import org.springframework.stereotype.Component

@Component
class InitializeRuleQueryDAO(private val databaseClient: DatabaseClient) {
    suspend fun initializeRule(ruleIdList: List<Int>) =
            databaseClient.select().from(InitializeRuleEntity::class.java)
                    .matching(where("RULE_ID").`in`(ruleIdList))
                    .`as`(InitializeRuleEntity::class.java)
                    .all().asFlow().toList()
}