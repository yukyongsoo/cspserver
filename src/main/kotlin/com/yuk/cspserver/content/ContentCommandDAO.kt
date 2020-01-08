package com.yuk.cspserver.content

import org.springframework.data.r2dbc.core.DatabaseClient
import org.springframework.stereotype.Component

@Component
class ContentCommandDAO(private val databaseClient: DatabaseClient) {

}