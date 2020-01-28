package com.yuk.cspserver.storage

import com.yuk.cspserver.common.BadRequestException
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.*

@Component
class StorageHandler(private val storageService: StorageService) {
    suspend fun getAllStorage(serverRequest: ServerRequest) : ServerResponse {
        return ServerResponse.ok().bodyValueAndAwait(storageService.getAllStorage())
    }

    suspend fun deleteStorage(serverRequest: ServerRequest) : ServerResponse {
        val storageId = serverRequest.pathVariable("storageId")
        storageService.deleteStorage(storageId)
        return ServerResponse.ok().buildAndAwait()
    }

    suspend fun addStorage(serverRequest: ServerRequest) : ServerResponse {
        val storageRequest = serverRequest.awaitBodyOrNull<StorageRequestDto>()
                ?: throw BadRequestException("storage add request has no body data.")
        storageService.addStorage(storageRequest)
        return ServerResponse.ok().buildAndAwait()
    }
}