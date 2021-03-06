package com.yuk.cspserver.content

import com.yuk.cspserver.common.BadRequestException
import com.yuk.cspserver.common.toIntCheck
import com.yuk.cspserver.element.ElementRequestDTO
import com.yuk.cspserver.element.file.filepart.ElementFileWriterPart
import kotlinx.coroutines.reactive.awaitFirst
import org.springframework.http.codec.multipart.FilePart
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.BodyInserters
import org.springframework.web.reactive.function.server.*
import java.net.URI

@Component
class ContentHandler(private val contentService: ContentService) {
    suspend fun createContent(serverRequest: ServerRequest): ServerResponse {
        val contentRequest = serverRequest.awaitBodyOrNull<ContentRequestDTO>()
                ?: throw BadRequestException("request body not found")
        val contentId = contentService.createContent(contentRequest)
        return ServerResponse.created(URI.create("/content/$contentId")).bodyValueAndAwait(contentId)
    }

    suspend fun getContent(serverRequest: ServerRequest): ServerResponse {
        val contentId = serverRequest.pathVariable("contentId")
        return ServerResponse.ok().bodyValueAndAwait(contentService.getContent(contentId))
    }

    suspend fun deleteContent(serverRequest: ServerRequest): ServerResponse {
        val contentId = serverRequest.pathVariable("contentId")
        contentService.deleteContent(contentId)
        return ServerResponse.ok().buildAndAwait()
    }

    suspend fun createContentElement(serverRequest: ServerRequest): ServerResponse {
        val contentId = serverRequest.pathVariable("contentId")
        val elementTypeId = serverRequest.queryParamOrNull("elementTypeId")?.toIntCheck()
                ?: throw BadRequestException("query String elementTypeId not Found")
        val fileParts = serverRequest.awaitMultipartData()["file"]
                ?: throw BadRequestException("file part not found")
        val file = if (fileParts.size == 1) fileParts[0] as FilePart
        else throw BadRequestException("only one file upload for save. you have ${fileParts.size}")
        val element = ElementRequestDTO(contentId, elementTypeId, ElementFileWriterPart(file))
        val elementId = contentService.createContentElement(element)
        return ServerResponse.created(URI.create("/content/${element.contentId}/$elementId")).bodyValueAndAwait(elementId)
    }

    suspend fun getContentElement(serverRequest: ServerRequest): ServerResponse {
        val contentId = serverRequest.pathVariable("contentId")
        val elementId = serverRequest.pathVariable("elementId").toIntCheck()
        return ServerResponse.ok().bodyValueAndAwait(contentService.getContentElement(contentId, elementId))
    }

    suspend fun getContentFile(serverRequest: ServerRequest): ServerResponse {
        val contentId = serverRequest.pathVariable("contentId")
        val elementId = serverRequest.pathVariable("elementId").toIntCheck()
        val resource = contentService.getContentFile(contentId, elementId).getResource()
        return ServerResponse.ok().body(BodyInserters.fromResource(resource)).awaitFirst()
    }

    suspend fun deleteContentElement(serverRequest: ServerRequest): ServerResponse {
        val contentId = serverRequest.pathVariable("contentId")
        val elementId = serverRequest.pathVariable("elementId").toIntCheck()
        contentService.deleteContentElement(contentId,elementId)
        return ServerResponse.ok().buildAndAwait()
    }
}