package com.yuk.cspserver.filter

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean

@ConditionalOnMissingBean
class DiscardFilter : Filter