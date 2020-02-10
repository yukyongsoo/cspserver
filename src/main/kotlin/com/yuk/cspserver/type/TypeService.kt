package com.yuk.cspserver.type

import com.yuk.cspserver.common.BadRequestException
import com.yuk.cspserver.common.BadStateException
import org.springframework.stereotype.Service

@Service
class TypeService(private val typeQueryDAO: TypeQueryDAO,
                  private val typeCommandDAO: TypeCommandDAO) {
    suspend fun getContentType(contentTypeId: Int): TypeResponseDTO {
        val type = getType(contentTypeId)
        if (type.category != TypeCategory.CONTENT)
            throw BadRequestException("request type is not content Type. id is ${type.id}")
        return type
    }

    suspend fun getElementType(elementTypeId: Int): TypeResponseDTO {
        val type = getType(elementTypeId)
        if (type.category != TypeCategory.CONTENT)
            throw BadRequestException("request type is not element Type. id is ${type.id}")
        return type
    }

    suspend fun getAllType(): List<TypeResponseDTO> {
        val typeList = typeQueryDAO.getAllType()
        return typeList.map {
            convertEntityToDTO(it)
        }
    }

    suspend fun getTypeDetail(typeId: Int): Any {
        //TODO :: get Rule and MetaData
        val type = getType(typeId)
        return type
    }

    suspend fun addType(typeRequest: TypeRequestDTO) {
        typeQueryDAO.findByName(typeRequest.name)?.run { throw BadRequestException("type name was Duplicated.") }
        typeCommandDAO.addType(typeRequest.name, typeRequest.category)
    }

    suspend fun deleteType(typeId: Int) {
        //TODO :: check rule and metadata,element
        typeCommandDAO.deleteType(typeId)
    }

    private suspend fun getType(typeId: Int): TypeResponseDTO {
        val type = typeQueryDAO.getType(typeId)
                ?: throw BadStateException("Type $typeId not found")
        return convertEntityToDTO(type)
    }

    private fun convertEntityToDTO(typeReadEntity: TypeReadEntity): TypeResponseDTO {
        return when (typeReadEntity.category) {
            1 -> TypeResponseDTO(typeReadEntity.id, typeReadEntity.name, TypeCategory.CONTENT)
            2 -> TypeResponseDTO(typeReadEntity.id, typeReadEntity.name, TypeCategory.ELEMENT)
            else -> throw BadStateException("type has not supported Category. category key is ${typeReadEntity.category}")
        }
    }

}