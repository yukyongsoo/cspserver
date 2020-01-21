package com.yuk.cspserver.authentication

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean

@ConditionalOnMissingBean
class DiscardAuthentication : Authentication {
    override fun check() {

    }
}