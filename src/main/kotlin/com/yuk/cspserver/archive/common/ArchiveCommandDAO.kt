package com.yuk.cspserver.archive.common

import org.springframework.data.r2dbc.core.DatabaseClient
import org.springframework.stereotype.Component

@Component
class ArchiveCommandDAO(private val databaseClient: DatabaseClient) {
}