package com.yuk.cspserver.element.type

import org.springframework.data.r2dbc.core.DatabaseClient
import org.springframework.stereotype.Component

@Component
class ElementTypeCommandDAO(private val databaseClient: DatabaseClient) {
}