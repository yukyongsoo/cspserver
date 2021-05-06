package com.yuk.cspserver.archive

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table("CSP_ARCHIVE")
data class ArchiveReadEntity(@Id val id: Int,
                             val name: String)

@Table("CSP_ARCHIVE")
data class ArchiveEntity(val name: String)



