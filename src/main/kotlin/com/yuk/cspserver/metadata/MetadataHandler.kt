package com.yuk.cspserver.metadata

import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.buildAndAwait

@Component
class MetadataHandler {
    suspend fun deleteMetaData(serverRequest: ServerRequest): ServerResponse {
        return ServerResponse.ok().buildAndAwait()
    }
}