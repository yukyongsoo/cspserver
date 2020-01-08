package com.yuk.cspserver.content

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.yuk.cspserver.content.type.ContentTypeDTO
import kotlinx.coroutines.reactive.awaitFirstOrElse
import org.springframework.http.codec.multipart.FilePart
import org.springframework.http.codec.multipart.FormFieldPart
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.buildAndAwait
import java.net.URI

@Component
class ContentHandler(private val contentService: ContentService,
                     private val objectMapper: ObjectMapper) {

    suspend fun createContent(serverRequest: ServerRequest): ServerResponse {
        val multiParts = serverRequest.multipartData().awaitFirstOrElse { throw IllegalArgumentException("multi part data not found") }
        val dataParts = multiParts.getFirst("data")
                ?.run { this as FormFieldPart }?.value() ?: throw IllegalArgumentException("data part not found")
        val contentMetadata = objectMapper.readValue<ContentTypeDTO>(dataParts)
        val fileParts = multiParts["file"]?.map { it as FilePart } ?: throw IllegalArgumentException("file part not found")
        //file part wrapping by class
        val content = ContentRequestDTO(contentMetadata,fileParts)
        return ServerResponse.created(URI.create(contentService.saveContent())).buildAndAwait()
    }
}