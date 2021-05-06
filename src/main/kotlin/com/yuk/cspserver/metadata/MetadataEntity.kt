package com.yuk.cspserver.metadata

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table

@Table("CSP_TYPE_METADATA")
data class MetadataEntity(@Id @Column("TYPE_ID") val typeId: Int,
                          @Column("META_CLASS") val metaClass: Int,
                          @Column("META_NAME") val metaName: String)