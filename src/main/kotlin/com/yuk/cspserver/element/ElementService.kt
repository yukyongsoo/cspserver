package com.yuk.cspserver.element

import com.yuk.cspserver.archive.ArchiveService
import com.yuk.cspserver.element.type.ElementTypeService
import com.yuk.cspserver.rule.RuleService
import org.springframework.stereotype.Service

@Service
class ElementService(private val elementTypeService: ElementTypeService,
                     private val ruleService: RuleService,
                     private val archiveService: ArchiveService) {
    suspend fun createElement(element: ElementRequestDTO): String {
        val elementType = elementTypeService.getType(element.elementTypeId)
        val initializeRules = ruleService.getInitializeRule(elementType.id)
        initializeRules.forEach {



        }
        return ""
    }
}