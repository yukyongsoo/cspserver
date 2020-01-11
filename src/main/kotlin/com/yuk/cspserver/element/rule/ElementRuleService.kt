package com.yuk.cspserver.element.rule

import org.springframework.stereotype.Service

@Service
class ElementRuleService(private val elementRuleQueryDAO: ElementRuleQueryDAO) {
    suspend fun getInitializeRule(elementTypeId: Int): Any {
        elementRuleQueryDAO.findByElementTypeId(elementTypeId)
    }
}