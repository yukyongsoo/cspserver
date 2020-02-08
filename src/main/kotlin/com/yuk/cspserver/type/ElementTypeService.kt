package com.yuk.cspserver.type

import com.yuk.cspserver.common.BadStateException
import org.springframework.stereotype.Service

@Service
class ElementTypeService(private val elementTypeQueryDAO: ElementTypeQueryDAO,
                         private val elementTypeCommandDAO: ElementTypeCommandDAO) {
    suspend fun getType(elementTypeId: Int): ElementTypeDTO {
        val elementType = elementTypeQueryDAO.getType(elementTypeId)
                ?: throw BadStateException("elementType $elementTypeId not found")
        return ElementTypeDTO(elementType.id)
    }
}