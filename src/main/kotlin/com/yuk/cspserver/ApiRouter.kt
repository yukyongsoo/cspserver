package com.yuk.cspserver

import com.yuk.cspserver.archive.ArchiveHandler
import com.yuk.cspserver.content.ContentHandler
import com.yuk.cspserver.storage.StorageHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.buildAndAwait
import org.springframework.web.reactive.function.server.coRouter

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
            GET("",storageHandler::getAllStorage)
        }
    }

    @Bean
    fun setArchiveRouter() = coRouter {
        "/archive".nest {
            GET("",archiveHandler::getAllArchive)
        }
    }

    @Bean
    fun setContentRouter() = coRouter {
        "/content".nest {
            POST("",contentHandler::createContent)
            GET("/{id}",contentHandler::getContent)
        }
    }
}