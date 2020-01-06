package com.yuk.cspserver.archive

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table("CSP_ARCHIVE")
data class ArchiveEntity(@Id val id: Int,
                         val name: String)
