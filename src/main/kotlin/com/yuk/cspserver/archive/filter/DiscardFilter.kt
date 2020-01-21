package com.yuk.cspserver.archive.filter

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean

@ConditionalOnMissingBean
class DiscardFilter : Filter