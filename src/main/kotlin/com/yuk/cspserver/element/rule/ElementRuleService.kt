package com.yuk.cspserver.element.rule

import com.yuk.cspserver.archive.ArchiveService
import com.yuk.cspserver.element.rule.initalize.InitializeRuleCommandDAO
import com.yuk.cspserver.element.rule.initalize.InitializeRuleQueryDAO
import com.yuk.cspserver.element.rule.initalize.InitializeRuleDTO
import org.springframework.stereotype.Service

@Service
class ElementRuleService(private val elementRuleQueryDAO: ElementRuleQueryDAO,
                         private val elementRuleCommandDAO: ElementRuleCommandDAO,
                         private val initializeRuleCommandDAO: InitializeRuleCommandDAO,
                         private val archiveService: ArchiveService) {
    suspend fun getInitializeRule(elementTypeId: Int): List<InitializeRuleDTO> {
        val list = elementRuleQueryDAO.findByElementTypeIdAndRuleType(elementTypeId, ElementRuleType.INITIALIZE)
        return if (list.isNotEmpty()) list
        else throw IllegalStateException("can't found any InitializeRule for elementType $elementTypeId")
    }

    suspend fun createInitializeRule(elementTypeId: Int,archiveId : Int) {
        val ruleId = elementRuleCommandDAO.createRule(elementTypeId,ElementRuleType.INITIALIZE.typeId)
        archiveService.getArchive(archiveId)
        initializeRuleCommandDAO.createInitializeRule(ruleId,archiveId)
    }
}