package com.yuk.cspserver.element

import com.yuk.cspserver.element.file.ElementFileService
import com.yuk.cspserver.element.file.filepart.ElementFileReader
import com.yuk.cspserver.element.file.filepart.ElementFileWriter
import com.yuk.cspserver.element.rule.ElementRuleService
import com.yuk.cspserver.element.type.ElementTypeService
import org.springframework.stereotype.Service

@Service
class ElementService(private val elementTypeService: ElementTypeService,
                     private val elementRuleService: ElementRuleService,
                     private val elementFileService: ElementFileService,
                     private val elementQueryDAO: ElementQueryDAO,
                     private val elementCommandDAO: ElementCommandDAO) {

    suspend fun createElement(element: ElementRequestDTO): String {
        val elementType = elementTypeService.getType(element.elementTypeId)
        val initializeRules = elementRuleService.getInitializeRule(elementType.id)
        val elementId = elementCommandDAO.createElement(element.elementFileWriter.getName(), element.contentId, elementType.id)?.run { this as Int }
                ?: throw IllegalStateException("can't save element, contentId is ${element.contentId}")
        initializeRules.forEach {
            elementFileService.saveFile(it.archiveId, elementId, element)
        }
        return elementId.toString()
    }

    suspend fun getElement(elementId: String) =
            elementQueryDAO.getElement(elementId)?.run {
                ElementResponseDTO(this.id, this.contentId, this.type, this.name)
            } ?: throw IllegalStateException("can't get element, elementID is $elementId")

    suspend fun getElementFile(elementId: String): ElementFileReader {
        val element = getElement(elementId)
        return elementFileService.getFile(element.id,element.contentId)
    }

}
