package com.yuk.cspserver.element.rule

import com.yuk.cspserver.element.rule.type.InitializeRuleDTO
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.reactive.asFlow
import org.springframework.data.r2dbc.core.DatabaseClient
import org.springframework.stereotype.Component

@Component
class ElementRuleQueryDAO(private val databaseClient: DatabaseClient) {
    suspend fun findByElementTypeIdAndRuleType(elementTypeId: Int, elementRuleType : ElementRuleType) =
            databaseClient.execute("select r.ELEMENT_TYPE_ID,r.RULE_ID,r.RULE_TYPE,i.ARCHIVE_ID\n" +
                    "from CSP_ELEMENT_TYPE_RULE as r join CSP_RULE_INIT as i on r.RULE_ID = i.RULE_ID\n" +
                    "and r.ELEMENT_TYPE_ID = :elementTypeId\n" +
                    "and r.RULE_TYPE = :ruleType")
                    .bind("elementTypeId",elementTypeId)
                    .bind("ruleType",(elementRuleType.ordinal))
                    .`as`(InitializeRuleDTO::class.java)
                    .fetch().all().asFlow().toList()
}