package com.yuk.cspserver.element.file

import org.springframework.data.r2dbc.core.DatabaseClient
import org.springframework.stereotype.Component

@Component
class ElementFileCommandDAO(private val databaseClient: DatabaseClient) {

}