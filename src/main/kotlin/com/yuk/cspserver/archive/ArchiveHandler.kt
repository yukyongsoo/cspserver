package com.yuk.cspserver.archive

import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.buildAndAwait

@Component
class ArchiveHandler(private val archiveService: ArchiveService) {

    suspend fun getAllArchive(serverRequest: ServerRequest) : ServerResponse{
        return ServerResponse.ok().buildAndAwait()
    }
}