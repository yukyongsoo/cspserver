package com.yuk.cspserver.element

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table

@Table("CSP_ELEMENT")
data class ElementEntity(@Column("CONTENT_ID") val contentId: String,
                         val type : Int,
                         val name : String)

@Table("CSP_ELEMENT")
data class ElementReadEntity(@Id val id : Int,
                             @Column("CONTENT_ID") val contentId: String,
                             val type : Int,
                             val name : String)