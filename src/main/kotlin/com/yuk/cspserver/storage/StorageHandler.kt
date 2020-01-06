package com.yuk.cspserver.storage

import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.buildAndAwait

@Component
class StorageHandler {
    suspend fun getAllStorage(serverRequest: ServerRequest) : ServerResponse {
        return ServerResponse.ok().buildAndAwait()
    }
}