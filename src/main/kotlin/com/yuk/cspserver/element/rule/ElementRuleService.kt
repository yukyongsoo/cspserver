package com.yuk.cspserver.element.rule

import com.yuk.cspserver.element.rule.type.InitializeRuleDTO
import org.springframework.stereotype.Service

@Service
class ElementRuleService(private val elementRuleQueryDAO: ElementRuleQueryDAO) {
    suspend fun getInitializeRule(elementTypeId: Int): List<InitializeRuleDTO> {
        val list = elementRuleQueryDAO.findByElementTypeIdAndRuleType(elementTypeId, ElementRuleType.INITIALIZE)
        return if (list.isNotEmpty()) list
        else throw IllegalStateException("can't found any InitializeRule for elementType $elementTypeId")
    }
}