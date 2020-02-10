package com.yuk.cspserver.metadata.dataeset

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table

@Table("CSP_METADATA_SET")
data class MetadataSetEntity(@Id val id: String,
                             @Column("META_CLASS") val metaClass: Int,
                             @Column("META_NAME") val metaName: String,
                             @Column("META_VALUE") val metaValue: String)