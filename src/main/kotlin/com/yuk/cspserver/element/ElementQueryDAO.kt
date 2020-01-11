package com.yuk.cspserver.element

import org.springframework.data.r2dbc.core.DatabaseClient
import org.springframework.stereotype.Component

@Component
class ElementQueryDAO(private val databaseClient: DatabaseClient) {

    fun createElement(name: String, contentId: String, id: Int) {

    }
}