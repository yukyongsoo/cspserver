package com.yuk.cspserver.type

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table("CSP_TYPE")
data class TypeReadEntity(@Id val id: Int,
                          val category: Int,
                          val name: String)

@Table("CSP_TYPE")
data class TypeEntity(val category: Int,
                      val name: String)