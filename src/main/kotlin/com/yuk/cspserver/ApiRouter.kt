package com.yuk.cspserver

import com.yuk.cspserver.archive.ArchiveHandler
import com.yuk.cspserver.authentication.AuthenticationHandler
import com.yuk.cspserver.content.ContentHandler
import com.yuk.cspserver.metadata.MetaDataHandler
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
                private val metaDataHandler: MetaDataHandler,
                private val authenticationHandler: AuthenticationHandler) {

    @Bean
    fun setRootRouter() = coRouter {
        GET("") { ServerResponse.ok().buildAndAwait() }
        POST("/login", authenticationHandler::login)
    }

    @Bean
    fun setStorageRouter() = coRouter {
        "/storage".nest {
            before {
                authenticationHandler.check(it)
                it
            }

            GET("", storageHandler::getAllStorage)

            POST("").and(accept(MediaType.APPLICATION_JSON))(storageHandler::addStorage)
            DELETE("/{storageId}", storageHandler::deleteStorage)
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
            GET("{archiveId}", archiveHandler::getArchive)
            POST("").and(accept(MediaType.APPLICATION_JSON))(archiveHandler::addArchive)
            DELETE("/{archiveId}", archiveHandler::deleteArchive)

            "/{archiveId}/{storageId}".nest {
                POST("", archiveHandler::addArchiveStorage)
                DELETE("", archiveHandler::deleteArchiveStorage)
            }
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
                    .and(accept(MediaType.APPLICATION_JSON))
                    .and(contentType(MediaType.APPLICATION_JSON))
            (contentHandler::createContent)

            "/{contentId}".nest {
                GET("", contentHandler::getContent)
                DELETE("", contentHandler::deleteContent)

                POST("")
                        .and(accept(MediaType.MULTIPART_FORM_DATA))
                        .and(contentType(MediaType.APPLICATION_JSON))
                        .and(queryParam("elementTypeId", { true }))
                (contentHandler::createContentElement)
            }

            "/{contentId}/{elementId}".nest {
                HEAD("", contentHandler::getContentElement)
                GET("", contentHandler::getContentFile)
                DELETE("", contentHandler::deleteContentElement)
            }
        }
    }

    @Bean
    fun setMetaDataRouter() = coRouter {
        "/metadata".nest {
            before {
                authenticationHandler.check(it)
                it
            }





        }
    }
}