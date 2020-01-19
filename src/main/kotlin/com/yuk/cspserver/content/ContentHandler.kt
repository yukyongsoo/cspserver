package com.yuk.cspserver.content

import com.yuk.cspserver.element.ElementRequestDTO
import com.yuk.cspserver.element.file.filepart.ElementFileWriterPart
import org.springframework.http.MediaType
import org.springframework.http.client.MultipartBodyBuilder
import org.springframework.http.codec.multipart.FilePart
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.BodyInserters
import org.springframework.web.reactive.function.server.*
import java.net.URI

@Component
class ContentHandler(private val contentService: ContentService) {
    suspend fun createContent(serverRequest: ServerRequest): ServerResponse {
        val contentRequest = serverRequest.awaitBodyOrNull<ContentRequestDTO>()
                ?: throw IllegalArgumentException("request body not found")
        val contentId = contentService.createContent(contentRequest)
        return ServerResponse.created(URI.create("/content/$contentId")).bodyValueAndAwait(contentId)
    }

    suspend fun getContent(serverRequest: ServerRequest): ServerResponse {
        val contentId = serverRequest.pathVariable("id")
        return ServerResponse.ok().bodyValueAndAwait(contentService.getContent(contentId))
    }

    suspend fun createContentElement(serverRequest: ServerRequest): ServerResponse {
        val contentId = serverRequest.pathVariable("contentId")
        val elementTypeId = serverRequest.queryParamOrNull("elementTypeId")?.toInt()
                ?: throw IllegalArgumentException("query String elementTypeId not Found")
        val fileParts = serverRequest.awaitMultipartData()["file"]
                ?: throw IllegalArgumentException("file part not found")
        val file = if (fileParts.size == 1) fileParts[0] as FilePart
        else throw IllegalArgumentException("only one file upload for save. you have ${fileParts.size}")
        val element = ElementRequestDTO(contentId, elementTypeId, ElementFileWriterPart(file))
        val elementId = contentService.createContentElement(element)
        return ServerResponse.created(URI.create("/content/${element.contentId}/$elementId")).bodyValueAndAwait(elementId)
    }

    suspend fun getContentElement(serverRequest: ServerRequest): ServerResponse {
        val contentId = serverRequest.pathVariable("contentId")
        val elementId = serverRequest.pathVariable("elementId")
        return ServerResponse.ok().bodyValueAndAwait(contentService.getContentElement(contentId, elementId))
    }

    suspend fun getContentFile(serverRequest: ServerRequest): ServerResponse {
        val contentId = serverRequest.pathVariable("contentId")
        val elementId = serverRequest.pathVariable("elementId")
        val resource = contentService.getContentFile(contentId, elementId).getResource()
        return ServerResponse.ok().bodyValueAndAwait(BodyInserters.fromResource(resource))
    }
}