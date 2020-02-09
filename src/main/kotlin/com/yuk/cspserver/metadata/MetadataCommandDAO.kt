package com.yuk.cspserver.metadata

import org.springframework.data.r2dbc.core.DatabaseClient
import org.springframework.stereotype.Component

@Component
class MetadataCommandDAO(private val databaseClient: DatabaseClient) {
}