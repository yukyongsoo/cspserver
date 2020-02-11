package com.yuk.cspserver.type

import com.yuk.cspserver.common.BadRequestException
import com.yuk.cspserver.common.toIntCheck
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.*

@Component
class TypeHandler(private val typeService: TypeService) {
    suspend fun getAllType(serverRequest: ServerRequest): ServerResponse {
        val typeList = typeService.getAllType()
        return ServerResponse.ok().bodyValueAndAwait(typeList)
    }

    suspend fun getTypeDetail(serverRequest: ServerRequest): ServerResponse {
        val typeId = serverRequest.pathVariable("typeId").toIntCheck()
        val type = typeService.getTypeDetail(typeId)
        return ServerResponse.ok().bodyValueAndAwait(type)
    }


    suspend fun addType(serverRequest: ServerRequest): ServerResponse {
        val typeRequest = serverRequest.awaitBodyOrNull<TypeRequestDTO>()
                ?: throw BadRequestException("current request has not Body Data")
        val typeId = typeService.addType(typeRequest)
        return ServerResponse.ok().bodyValueAndAwait(typeId)
    }

    suspend fun deleteType(serverRequest: ServerRequest): ServerResponse {
        val typeId = serverRequest.pathVariable("typeId").toIntCheck()
        typeService.deleteType(typeId)
        return ServerResponse.ok().buildAndAwait()
    }
}