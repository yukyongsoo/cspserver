package com.yuk.cspserver.rule

import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.buildAndAwait

@Component
class RuleHandler {
    suspend fun deleteRule(serverRequest: ServerRequest): ServerResponse {
        return ServerResponse.ok().buildAndAwait()
    }
}