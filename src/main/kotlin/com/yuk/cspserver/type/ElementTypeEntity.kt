package com.yuk.cspserver.type

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table("CSP_ELEMENT_TYPE")
data class ElementTypeEntity(@Id val id : Int,
                             val name : String)