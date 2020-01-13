package com.yuk.cspserver.element.file

import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table

@Table("CSP_ELEMENT_FILE")
data class ElementFileEntity(@Column("ELEMENT_ID") val elementId: Int,
                             @Column("ARCHIVE_ID") val archiveId: Int,
                             @Column("STORAGE_PATH") val storagePath: String,
                             @Column("FILE_PATH") val filePath: String)