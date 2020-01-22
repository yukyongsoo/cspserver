package com.yuk.cspserver

import com.yuk.cspserver.archive.ArchiveHandler
import com.yuk.cspserver.authentication.AuthenticationHandler
import com.yuk.cspserver.content.ContentHandler
import com.yuk.cspserver.storage.StorageHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.buildAndAwait
import org.springframework.web.reactive.function.server.coRouter

@Configuration
class ApiRouter(private val storageHandler: StorageHandler,
                private val archiveHandler: ArchiveHandler,
                private val contentHandler: ContentHandler,
                private val authenticationHandler: AuthenticationHandler) {

    @Bean
    fun setRootRouter() = coRouter {
        GET("") { ServerResponse.ok().buildAndAwait() }
        POST("",authenticationHandler::login)
    }

    @Bean
    fun setStorageRouter() = coRouter {
        "/storage".nest {
            before {
                authenticationHandler.check(it)
                it
            }

            GET("", storageHandler::getAllStorage)
        }
    }

    @Bean
    fun setArchiveRouter() = coRouter {
        "/archive".nest {
            before {
                authenticationHandler.check(it)
                it
            }

            GET("", archiveHandler::getAllArchive)
        }
    }

    @Bean
    fun setContentRouter() = coRouter {
        "/content".nest {
            before {
                authenticationHandler.check(it)
                it
            }

            POST("")
            accept(MediaType.APPLICATION_JSON)
            contentType(MediaType.APPLICATION_JSON, contentHandler::createContent)

            "/{contentId}".nest {
                GET("", contentHandler::getContent)
                DELETE("",contentHandler::deleteContent)

                POST("")
                accept(MediaType.MULTIPART_FORM_DATA)
                contentType(MediaType.APPLICATION_JSON)
                queryParam("elementTypeId", { true }, contentHandler::createContentElement)
            }

            "/{contentId}/{elementId}".nest {
                HEAD("",contentHandler::getContentElement)
                GET("", contentHandler::getContentFile)
                DELETE("", contentHandler::deleteContentElement)
            }
        }
    }


}