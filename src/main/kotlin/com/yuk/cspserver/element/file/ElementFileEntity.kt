package com.yuk.cspserver.element.file

import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table

@Table("CSP_ELEMENT_FILE")
data class ElementFileEntity(@Column("ELEMENT_ID") val elementId: Int,
                             @Column("ROOT_PATH") val rootPath: String,
                             @Column("ARCHIVE_PATH") val archivePath: String,
                             @Column("FILE_PATH") val filePath: String)