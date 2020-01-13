package com.yuk.cspserver.storage

import org.springframework.data.relational.core.mapping.Table

@Table("CSP_STORAGE")
data class StorageEntity(val id: Int, val name: String, val usable: Boolean, val path: String)