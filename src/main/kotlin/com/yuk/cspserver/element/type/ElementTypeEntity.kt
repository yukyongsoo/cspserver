package com.yuk.cspserver.element.type

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table("CSP_ELEMNET_TYPE")
data class ElementTypeEntity(@Id val id : Int,
                             val name : String)