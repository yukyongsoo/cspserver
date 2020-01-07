package com.yuk.cspserver.archive.archivestorage

import org.springframework.data.relational.core.mapping.Table

@Table("CSP_ARCHIVE_STORAGE")
data class ArchiveStorageEntity(val archiveId : Int,
                                val storageId : Int)