package com.yuk.cspserver.type

import com.yuk.cspserver.common.BadRequestException
import com.yuk.cspserver.common.BadStateException
import org.springframework.stereotype.Service

@Service
class TypeService(private val typeQueryDAO: TypeQueryDAO,
                  private val typeCommandDAO: TypeCommandDAO) {
    suspend fun getContentType(contentTypeId: Int): TypeDTO {
        val type = getType(contentTypeId)
        if (type.category != TypeCategory.CONTENT)
            throw BadRequestException("request type is not content Type. id is ${type.id}")
        return type
    }

    suspend fun getElementType(elementTypeId: Int): TypeDTO {
        val type = getType(elementTypeId)
        if (type.category != TypeCategory.CONTENT)
            throw BadRequestException("request type is not content Type. id is ${type.id}")
        return type
    }

    private suspend fun getType(typeId: Int): TypeDTO {
        val type = typeQueryDAO.getType(typeId)
                ?: throw BadStateException("Type $typeId not found")
        return when (type.category) {
            1 -> TypeDTO(type.id, type.name, TypeCategory.CONTENT)
            2 -> TypeDTO(type.id, type.name, TypeCategory.ELEMENT)
            else -> throw BadStateException("type has not supported Category. category key is ${type.category}")
        }
    }

}