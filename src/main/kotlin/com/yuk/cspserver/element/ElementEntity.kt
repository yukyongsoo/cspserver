package com.yuk.cspserver.element

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table("CSP_ELEMENT")
data class ElementEntity(@Id val elementId: Int,
                         val contentId: String)