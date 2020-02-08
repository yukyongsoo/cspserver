package com.yuk.cspserver.type

import org.springframework.stereotype.Service

@Service
class ContentTypeService(private val contentTypeQueryDAO: ContentTypeQueryDAO) {
    suspend fun getElementType(contentTypeId: Int): ContentTypeDTO? =
            contentTypeQueryDAO.getType(contentTypeId)?.let {
                ContentTypeDTO(it.id, it.name)
            }
}