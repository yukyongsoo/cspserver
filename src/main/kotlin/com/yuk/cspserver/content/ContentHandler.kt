package com.yuk.cspserver.content

import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.*
import java.net.URI

@Component
class ContentHandler(private val contentService: ContentService) {
    suspend fun createContent(serverRequest: ServerRequest): ServerResponse {
        val contentRequest = serverRequest.awaitBodyOrNull<ContentRequestDTO>() ?: throw IllegalArgumentException("request body not found")
        return ServerResponse.created(URI.create(contentService.createContent(contentRequest))).buildAndAwait()
    }

    suspend fun getContent(serverRequest: ServerRequest) : ServerResponse {
        val contentId = serverRequest.pathVariable("id")
        return ServerResponse.ok().bodyValueAndAwait(contentService.getContent(contentId))
    }
}