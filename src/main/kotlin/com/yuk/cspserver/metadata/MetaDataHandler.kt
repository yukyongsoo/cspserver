package com.yuk.cspserver.metadata

import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.*

@Component
class MetaDataHandler {
    suspend fun deleteMetaData(serverRequest: ServerRequest) : ServerResponse {
        return ServerResponse.ok().buildAndAwait()
    }
}