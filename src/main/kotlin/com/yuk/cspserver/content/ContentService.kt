package com.yuk.cspserver.content

import com.yuk.cspserver.content.type.ContentTypeService
import org.springframework.stereotype.Service
import java.util.*

@Service
class ContentService(private val contentCommandDAO: ContentCommandDAO,
                     private val contentQueryDAO: ContentQueryDAO,
                     private val contentTypeService: ContentTypeService) {
    suspend fun createContent(contentRequest: ContentRequestDTO): String {
        val elementType = contentTypeService.getElementType(contentRequest.contentTypeName)
                ?: throw IllegalArgumentException("content Type Not Found. ${contentRequest.contentTypeName}")
        val contentId = UUID.randomUUID().toString()
        contentCommandDAO.createContent(contentId, elementType.name)
        return "/content/$contentId"
    }

    suspend fun getContent(contentId: String) =
            contentQueryDAO.getContent(contentId)?.let {
                ContentResponseDTO(it.id, it.type)
            } ?: throw IllegalStateException("content $contentId not found")
}