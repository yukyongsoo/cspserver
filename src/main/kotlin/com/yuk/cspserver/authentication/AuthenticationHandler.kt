package com.yuk.cspserver.authentication

import kotlinx.coroutines.runBlocking
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.awaitBodyOrNull
import org.springframework.web.reactive.function.server.bodyValueAndAwait

@Component
class AuthenticationHandler(private val authenticationService: AuthenticationService) {
    suspend fun login(serverRequest: ServerRequest) : ServerResponse{
        val headers = serverRequest.headers().asHttpHeaders().toMap()
        val body = serverRequest.awaitBodyOrNull<String>()
        return ServerResponse.ok().bodyValueAndAwait(authenticationService.login(headers,body))
    }

    fun check(serverRequest: ServerRequest) = runBlocking {
        val headers = serverRequest.headers().asHttpHeaders().toMap()
        authenticationService.check(headers)
    }
}