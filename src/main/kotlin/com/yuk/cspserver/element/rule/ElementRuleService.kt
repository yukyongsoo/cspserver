package com.yuk.cspserver.element.rule

import com.yuk.cspserver.element.rule.initalize.InitializeRuleCommandDAO
import com.yuk.cspserver.element.rule.initalize.InitializeRuleQueryDAO
import com.yuk.cspserver.element.rule.initalize.InitializeRuleDTO
import org.springframework.stereotype.Service

@Service
class ElementRuleService(private val elementRuleQueryDAO: ElementRuleQueryDAO,
                         private val elementRuleCommandDAO: ElementRuleCommandDAO,
                         private val initializeRuleCommandDAO: InitializeRuleCommandDAO) {
    suspend fun getInitializeRule(elementTypeId: Int): List<InitializeRuleDTO> {
        val list = elementRuleQueryDAO.findByElementTypeIdAndRuleType(elementTypeId, ElementRuleType.INITIALIZE)
        return if (list.isNotEmpty()) list
        else throw IllegalStateException("can't found any InitializeRule for elementType $elementTypeId")
    }

    suspend fun createInitializeRule(elementTypeId: Int) {
        elementRuleCommandDAO.createRule(elementTypeId,ElementRuleType.INITIALIZE.ordinal)
        //initializeRuleCommandDAO.createInitializeRule(r)
    }
}