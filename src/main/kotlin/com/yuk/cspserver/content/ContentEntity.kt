package com.yuk.cspserver.content

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table("CSP_CONTENT")
data class ContentEntity(@Id val id : String,
                         val type : String)