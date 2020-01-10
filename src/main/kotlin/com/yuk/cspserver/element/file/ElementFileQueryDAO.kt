package com.yuk.cspserver.element.file

import org.springframework.data.r2dbc.core.DatabaseClient
import org.springframework.stereotype.Component

@Component
class ElementFileQueryDAO(private val databaseClient: DatabaseClient) {

}