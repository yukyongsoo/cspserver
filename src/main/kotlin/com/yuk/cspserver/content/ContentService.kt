package com.yuk.cspserver.content

import com.yuk.cspserver.common.ChildFoundException
import com.yuk.cspserver.content.type.ContentTypeService
import com.yuk.cspserver.element.ElementRequestDTO
import com.yuk.cspserver.element.ElementResponseDTO
import com.yuk.cspserver.element.ElementService
import com.yuk.cspserver.element.file.filepart.ElementFileReader
import org.springframework.stereotype.Service
import java.util.*

@Service
class ContentService(private val contentCommandDAO: ContentCommandDAO,
                     private val contentQueryDAO: ContentQueryDAO,
                     private val contentTypeService: ContentTypeService,
                     private val elementService: ElementService) {
    suspend fun createContent(contentRequest: ContentRequestDTO): String {
        contentTypeService.getElementType(contentRequest.contentTypeId)
                ?: throw IllegalArgumentException("content Type Not Found. ${contentRequest.contentTypeId}")
        if (contentRequest.name.isBlank())
            throw  IllegalArgumentException("content must have name. you request is ${contentRequest.name}")
        val contentId = UUID.randomUUID().toString()
        contentCommandDAO.createContent(contentId, contentRequest)
        return contentId
    }

    suspend fun getContent(contentId: String) =
            contentQueryDAO.getContent(contentId)?.let {
                ContentResponseDTO(it.id, it.name, it.typeId)
            } ?: throw IllegalStateException("content $contentId not found")

    suspend fun deleteContent(contentId: String) {
        if(elementService.findByContentId(contentId).isNotEmpty())
            throw ChildFoundException("content $contentId has child Element. you must delete all Child")
        contentCommandDAO.deleteContent(contentId)
    }

    suspend fun createContentElement(element: ElementRequestDTO): String {
        contentQueryDAO.getContent(element.contentId)
                ?: throw IllegalStateException("content ${element.contentId} not found")
        return elementService.createElement(element)
    }

    suspend fun getContentElement(contentId: String, elementId: Int): ElementResponseDTO {
        contentQueryDAO.getContent(contentId)
                ?: throw IllegalArgumentException("can't find any content. id is $contentId")
        return elementService.getElement(elementId)
    }

    suspend fun getContentFile(contentId: String, elementId: Int): ElementFileReader {
        contentQueryDAO.getContent(contentId)
                ?: throw IllegalArgumentException("can't find any content. id is $contentId")
        return elementService.getElementFile(elementId)
    }

    suspend fun deleteContentElement(contentId: String, elementId: Int) {
        elementService.deleteElement(contentId,elementId)
    }
}