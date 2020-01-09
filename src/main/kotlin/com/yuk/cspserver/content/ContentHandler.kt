package com.yuk.cspserver.content

import com.yuk.cspserver.element.ElementRequestDTO
import com.yuk.cspserver.element.file.SpringFilePart
import org.springframework.http.codec.multipart.FilePart
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

    suspend fun createContentElement(serverRequest: ServerRequest) : ServerResponse {
        val contentId = serverRequest.pathVariable("contentId")
        val elementTypeId = serverRequest.queryParamOrNull("typeId")?.toInt() ?: throw IllegalArgumentException("query String elementTypeId not Found")
        val fileParts = serverRequest.awaitMultipartData()["file"] ?: throw IllegalArgumentException("data part not found")
        val element = ElementRequestDTO(contentId,elementTypeId, fileParts.map { SpringFilePart(it as FilePart) })
        return ServerResponse.created(URI.create(contentService.createContentElement(element))).buildAndAwait()
    }
}