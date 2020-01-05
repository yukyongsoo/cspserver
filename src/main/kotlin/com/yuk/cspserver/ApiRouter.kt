package com.yuk.cspserver

import com.yuk.cspserver.archive.ArchiveHandler
import com.yuk.cspserver.archive.storage.StorageHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.buildAndAwait
import org.springframework.web.reactive.function.server.coRouter

@Configuration
class ApiRouter(private val storageHandler: StorageHandler,
                private val archiveHandler: ArchiveHandler) {
    @Bean
    fun setRootRouter() = coRouter {
        GET("") { ServerResponse.ok().buildAndAwait() }
    }

    @Bean
    fun setStorageRouter() = coRouter {
        "/storage".nest {


        }
    }

    @Bean
    fun setArchiveRouter() = coRouter {
        "/archive".nest {

        }
    }

    @Bean
    fun setFileRouter() = coRouter {
        "/file".nest {

        }
    }
}