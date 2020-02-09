package com.yuk.cspserver.type

import org.springframework.data.r2dbc.core.DatabaseClient
import org.springframework.stereotype.Component

@Component
class TypeCommandDAO(private val databaseClient: DatabaseClient) {
}