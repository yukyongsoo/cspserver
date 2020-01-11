package com.yuk.cspserver.element

import com.yuk.cspserver.archive.ArchiveService
import com.yuk.cspserver.element.rule.ElementRuleService
import com.yuk.cspserver.element.type.ElementTypeService
import com.yuk.cspserver.rule.RuleService
import org.springframework.stereotype.Service

@Service
class ElementService(private val elementTypeService: ElementTypeService,
                     private val elementRuleService: ElementRuleService,
                     private val archiveService: ArchiveService,
                     private val elementQueryDAO: ElementQueryDAO) {
    suspend fun createElement(element: ElementRequestDTO): String {
        val elementType = elementTypeService.getType(element.elementTypeId)
        val initializeRules = elementRuleService.getInitializeRule(elementType.id)
        val elementId = elementQueryDAO.createElement(element.elementFile.getName(), element.contentId ,elementType.id)

        initializeRules.forEach {
            archiveService.saveFile(it.archiveId, elementId, element.elementFile)
        }
        return "/storage/${element.contentId}/$elementId"
    }
}
