package com.yuk.cspserver.content

import com.yuk.cspserver.common.BadRequestException
import com.yuk.cspserver.common.BadStateException
import com.yuk.cspserver.common.ChildFoundException
import com.yuk.cspserver.element.ElementComponent
import com.yuk.cspserver.element.ElementRequestDTO
import com.yuk.cspserver.element.ElementResponseDTO
import com.yuk.cspserver.element.file.filepart.ElementFileReader
import com.yuk.cspserver.type.TypeService
import org.springframework.stereotype.Service
import java.util.*

@Service
class ContentService(private val contentCommandDAO: ContentCommandDAO,
                     private val contentQueryDAO: ContentQueryDAO,
                     private val typeService: TypeService,
                     private val elementComponent: ElementComponent) {
    suspend fun createContent(contentRequest: ContentRequestDTO): String {
        val type = typeService.getContentType(contentRequest.contentTypeId)
        if (contentRequest.name.isBlank())
            throw  BadRequestException("content must have name. you request is ${contentRequest.name}")
        val contentId = UUID.randomUUID().toString()
        contentCommandDAO.createContent(contentId, contentRequest)
        return contentId
    }

    suspend fun getContent(contentId: String) =
            contentQueryDAO.getContent(contentId)?.let {
                ContentResponseDTO(it.id, it.name, it.typeId)
            } ?: throw BadStateException("content $contentId not found")

    suspend fun deleteContent(contentId: String) {
        if (elementComponent.findByContentId(contentId).isNotEmpty())
            throw ChildFoundException("content $contentId has child Element. you must delete all Child")
        contentCommandDAO.deleteContent(contentId)
    }

    suspend fun createContentElement(element: ElementRequestDTO): String {
        contentQueryDAO.getContent(element.contentId)
                ?: throw BadStateException("content ${element.contentId} not found")
        return elementComponent.createElement(element)
    }

    suspend fun getContentElement(contentId: String, elementId: Int): ElementResponseDTO {
        contentQueryDAO.getContent(contentId)
                ?: throw BadRequestException("can't find any content. id is $contentId")
        return elementComponent.getElement(elementId)
    }

    suspend fun getContentFile(contentId: String, elementId: Int): ElementFileReader {
        contentQueryDAO.getContent(contentId)
                ?: throw IllegalArgumentException("can't find any content. id is $contentId")
        return elementComponent.getElementFile(elementId)
    }

    suspend fun deleteContentElement(contentId: String, elementId: Int) {
        elementComponent.deleteElement(contentId, elementId)
    }
}