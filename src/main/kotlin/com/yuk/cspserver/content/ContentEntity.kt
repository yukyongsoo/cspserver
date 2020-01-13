package com.yuk.cspserver.content

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table

@Table("CSP_CONTENT")
data class ContentEntity(@Id val id : String,
                         @Column("TYPE_ID") val typeId : Int)