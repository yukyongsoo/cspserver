package com.yuk.cspserver.type.type.type

import org.springframework.data.r2dbc.core.DatabaseClient
import org.springframework.stereotype.Component

@Component
class ElementTypeCommandDAO(private val databaseClient: DatabaseClient) {
}