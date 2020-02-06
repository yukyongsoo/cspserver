package com.yuk.cspserver.content

import com.yuk.cspserver.metadata.MetadataContainer

data class ContentRequestDTO(val contentTypeId : Int,val name : String)

data class ContentResponseDTO(val id: String,val name : String, val contentTypeId: Int, val metadataContainer: MetadataContainer)