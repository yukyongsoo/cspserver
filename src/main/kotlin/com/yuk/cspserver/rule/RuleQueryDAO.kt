package com.yuk.cspserver.rule

import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.reactive.asFlow
import org.springframework.data.r2dbc.core.DatabaseClient
import org.springframework.data.r2dbc.query.Criteria.where
import org.springframework.stereotype.Component

@Component
class RuleQueryDAO(private val databaseClient: DatabaseClient) {
    suspend fun findByElementTypeIdAndRuleType(elementTypeId: Int, ruleType: RuleType) =
            databaseClient.select().from(RuleReadEntity::class.java)
                    .matching(where("TYPE_ID").`is`(elementTypeId).and("ruleType").`is`(ruleType.typeId))
                    .`as`(RuleReadEntity::class.java)
                    .all().asFlow().toList()
}