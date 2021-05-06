package com.yuk.cspserver.element

import com.yuk.cspserver.common.BadStateException
import com.yuk.cspserver.element.file.ElementFileComponent
import com.yuk.cspserver.element.file.filepart.ElementFileReader
import com.yuk.cspserver.rule.RuleService
import com.yuk.cspserver.type.TypeService
import org.springframework.stereotype.Component

@Component
class ElementComponent(private val typeService: TypeService,
                       private val ruleService: RuleService,
                       private val elementFileComponent: ElementFileComponent,
                       private val elementQueryDAO: ElementQueryDAO,
                       private val elementCommandDAO: ElementCommandDAO) {

    suspend fun createElement(element: ElementRequestDTO): String {
        val elementType = typeService.getElementType(element.elementTypeId)
        val initializeRules = ruleService.getInitializeRule(elementType.id)
        val elementId = elementCommandDAO.createElement(element.elementFileWriter.getName(), element.contentId, elementType.id)?.run { this as Int }
                ?: throw BadStateException("can't save element, contentId is ${element.contentId}")
        initializeRules.forEach {
            elementFileComponent.saveFile(it.archiveId, elementId, element)
        }
        return elementId.toString()
    }

    suspend fun getElement(elementId: Int) =
            elementQueryDAO.getElement(elementId)?.run {
                ElementResponseDTO(this.id, this.contentId, this.typeId, this.name)
            } ?: throw BadStateException("can't get element, elementID is $elementId")

    suspend fun getElementFile(elementId: Int): ElementFileReader {
        val element = getElement(elementId)
        return elementFileComponent.getFile(element.id, element.contentId)
    }

    suspend fun findByContentId(contentId: String) =
            elementQueryDAO.getElementByContentId(contentId).map {
                ElementResponseDTO(it.id, it.contentId, it.typeId, it.name)
            }

    suspend fun deleteElement(contentId: String, elementId: Int) {
        elementFileComponent.deleteFile(elementId,contentId)
        elementCommandDAO.deleteElement(contentId,elementId)
    }
}
