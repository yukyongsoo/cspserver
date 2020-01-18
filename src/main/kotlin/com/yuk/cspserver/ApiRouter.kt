package com.yuk.cspserver

import com.yuk.cspserver.archive.ArchiveHandler
import com.yuk.cspserver.content.ContentHandler
import com.yuk.cspserver.storage.StorageHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType
import org.springframework.web.reactive.function.server.*

@Configuration
class ApiRouter(private val storageHandler: StorageHandler,
                private val archiveHandler: ArchiveHandler,
                private val contentHandler: ContentHandler) {

    @Bean
    fun setRootRouter() = coRouter {
        GET("") { ServerResponse.ok().buildAndAwait() }
    }

    @Bean
    fun setStorageRouter() = coRouter {
        "/storage".nest {
            GET("", storageHandler::getAllStorage)
        }
    }

    @Bean
    fun setArchiveRouter() = coRouter {
        "/archive".nest {
            GET("", archiveHandler::getAllArchive)
        }
    }

    @Bean
    fun setContentRouter() = coRouter {
        "/content".nest {
            POST("").and(accept(MediaType.APPLICATION_JSON)).and(contentType(MediaType.APPLICATION_JSON)).invoke(contentHandler::createContent)
            GET("/{id}").and(accept(MediaType.APPLICATION_JSON)).and(contentType(MediaType.APPLICATION_JSON)).invoke(contentHandler::getContent)
            POST("/{contentId}").and(accept(MediaType.MULTIPART_FORM_DATA)).and(contentType(MediaType.MULTIPART_FORM_DATA))
                    .and(queryParam("elementTypeId") { true }).invoke(contentHandler::createContentElement)
            HEAD("/{contentId}/{elementId}").and(accept(MediaType.APPLICATION_JSON))
                    .and(contentType(MediaType.APPLICATION_JSON)).invoke(contentHandler::getContentElement)
            GET("/{contentId}/{elementId}").and(accept(MediaType.APPLICATION_JSON))
                    .and(contentType(MediaType.MULTIPART_FORM_DATA)).invoke(contentHandler::getContentFile)
        }
    }
}