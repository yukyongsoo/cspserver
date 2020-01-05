package com.yuk.cspserver

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.buildAndAwait
import org.springframework.web.reactive.function.server.coRouter

@Configuration
class ApiRouter() {
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