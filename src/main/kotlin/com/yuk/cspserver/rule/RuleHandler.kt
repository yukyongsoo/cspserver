package com.yuk.cspserver.rule

import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.buildAndAwait

@Component
class RuleHandler(private val ruleService: RuleService) {
    suspend fun addRule(serverRequest: ServerRequest) : ServerResponse{
        return ServerResponse.ok().buildAndAwait()
    }

    suspend fun deleteRule(serverRequest: ServerRequest): ServerResponse {
        return ServerResponse.ok().buildAndAwait()
    }

    suspend fun getAllRule(serverRequest: ServerRequest) : ServerResponse {
        return ServerResponse.ok().buildAndAwait()
    }

    suspend fun getRule(serverRequest: ServerRequest) : ServerResponse {
        return ServerResponse.ok().buildAndAwait()
    }
}