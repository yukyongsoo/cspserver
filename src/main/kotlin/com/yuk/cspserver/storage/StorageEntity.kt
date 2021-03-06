package com.yuk.cspserver.storage

import org.springframework.data.relational.core.mapping.Table

@Table("CSP_STORAGE")
data class StorageReadEntity(val id: Int, val name: String, val usable: Boolean, val type: Int, val path: String)

@Table("CSP_STORAGE")
data class StorageEntity(val name: String, val usable: Boolean, val type: Int, val path: String)