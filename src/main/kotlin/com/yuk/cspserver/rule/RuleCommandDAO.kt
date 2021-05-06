package com.yuk.cspserver.rule

import org.springframework.data.r2dbc.core.DatabaseClient
import org.springframework.data.r2dbc.core.awaitFirstOrNull
import org.springframework.data.r2dbc.core.awaitRowsUpdated
import org.springframework.data.r2dbc.query.Criteria.where
import org.springframework.stereotype.Component

@Component
class RuleCommandDAO(private val databaseClient: DatabaseClient) {
    suspend fun createRule(elementTypeId: Int, ruleType: Int) =
            databaseClient.insert().into(RuleEntity::class.java)
                    .using(RuleEntity(elementTypeId, ruleType))
                    .fetch()
                    .awaitFirstOrNull()?.get("id") as Int

    suspend fun deleteRule(typeId: Int, ruleId: Int) =
            databaseClient.delete().from(RuleEntity::class.java)
                    .matching(where("type_Id").`is`(typeId).and("id").`is`(ruleId))
                    .fetch()
                    .awaitRowsUpdated()
}