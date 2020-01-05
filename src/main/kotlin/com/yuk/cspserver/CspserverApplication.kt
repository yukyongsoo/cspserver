package com.yuk.cspserver

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity
import org.springframework.web.reactive.config.EnableWebFlux

@SpringBootApplication
@EnableWebFlux //do not need for auto configuration but this is test
@EnableWebFluxSecurity
@EnableR2dbcRepositories
class CspserverApplication

fun main(args: Array<String>) {
    runApplication<CspserverApplication>(*args)
}
