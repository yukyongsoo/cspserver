package com.yuk.cspserver.content.type

import org.springframework.stereotype.Service

@Service
class ContentTypeService(private val contentTypeQueryDAO: ContentTypeQueryDAO) {
    suspend fun getElementType(contentTypeName : String): ContentTypeDTO? =
        contentTypeQueryDAO.getTypeByName(contentTypeName)?.let {
            ContentTypeDTO(it.name)
        }
}