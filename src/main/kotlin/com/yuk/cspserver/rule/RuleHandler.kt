package com.yuk.cspserver.rule

import com.yuk.cspserver.common.toIntCheck
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.*

@Component
class RuleHandler(private val ruleService: RuleService) {
    suspend fun addRule(serverRequest: ServerRequest): ServerResponse {
        //TODO:: makeAddRule
        return ServerResponse.ok().buildAndAwait()
    }

    suspend fun deleteRule(serverRequest: ServerRequest): ServerResponse {
        val typeId = serverRequest.pathVariable("typeId").toIntCheck()
        val ruleId = serverRequest.pathVariable("ruleId").toIntCheck()
        ruleService.deleteRule(typeId, ruleId)
        return ServerResponse.ok().buildAndAwait()
    }

    suspend fun getAllRule(serverRequest: ServerRequest): ServerResponse {
        val ruleList = ruleService.getAllRule()
        return ServerResponse.ok().bodyValueAndAwait(ruleList)
    }

    suspend fun getTypeRule(serverRequest: ServerRequest): ServerResponse {
        val typeId = serverRequest.pathVariable("typeId")
        val ruleList = ruleService.getTypeRules(typeId)
        return ServerResponse.ok().bodyValueAndAwait(ruleList)
    }
}