package com.yuk.cspserver.metadata

import com.yuk.cspserver.metadata.type.MetadataClass

data class MetadataDTO(val typeId: Int,
                       val metaClass: MetadataClass,
                       val metaName: String)