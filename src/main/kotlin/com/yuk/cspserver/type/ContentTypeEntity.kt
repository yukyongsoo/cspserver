package com.yuk.cspserver.type

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table("CSP_CONTENT_TYPE")
data class ContentTypeEntity(@Id val id : Int, val name : String)