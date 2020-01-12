package com.yuk.cspserver.element

import com.yuk.cspserver.archive.ArchiveService
import com.yuk.cspserver.element.rule.ElementRuleService
import com.yuk.cspserver.element.type.ElementTypeService
import org.springframework.stereotype.Service

@Service
class ElementService(private val elementTypeService: ElementTypeService,
                     private val elementRuleService: ElementRuleService,
                     private val archiveService: ArchiveService,
                     private val elementQueryDAO: ElementQueryDAO,
                     private val elementCommandDAO: ElementCommandDAO) {
    suspend fun createElement(element: ElementRequestDTO): String {
        val elementType = elementTypeService.getType(element.elementTypeId)
        val initializeRules = elementRuleService.getInitializeRule(elementType.id)
        val elementId = elementCommandDAO.createElement(element.elementFile.getName(), element.contentId ,elementType.id) ?.run { this as Int }
                ?: throw IllegalStateException("can't save element, contentId is ${element.contentId}")
        initializeRules.forEach {
            archiveService.saveFile(it.archiveId, elementId, element.elementFile)
        }
        return "/storage/${element.contentId}/$elementId"
    }
}
