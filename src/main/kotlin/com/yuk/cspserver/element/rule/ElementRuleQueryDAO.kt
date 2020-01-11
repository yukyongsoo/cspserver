package com.yuk.cspserver.element.rule

import org.springframework.data.r2dbc.core.DatabaseClient
import org.springframework.stereotype.Component

@Component
class ElementRuleQueryDAO(private val databaseClient: DatabaseClient) {
}