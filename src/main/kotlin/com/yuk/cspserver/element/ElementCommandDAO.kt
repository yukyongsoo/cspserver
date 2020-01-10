package com.yuk.cspserver.element

import org.springframework.data.r2dbc.core.DatabaseClient
import org.springframework.stereotype.Component

@Component
class ElementCommandDAO(private val databaseClient: DatabaseClient) {
}