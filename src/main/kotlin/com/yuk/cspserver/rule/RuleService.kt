package com.yuk.cspserver.rule

import com.yuk.cspserver.rule.initialize.InitializeRule
import org.springframework.stereotype.Service

//TODO:: make rule entity and database
@Service
class RuleService(private val ruleManager: RuleManager) {
    fun getInitializeRule(id: Int): List<InitializeRule> {
        return listOf()
    }
}