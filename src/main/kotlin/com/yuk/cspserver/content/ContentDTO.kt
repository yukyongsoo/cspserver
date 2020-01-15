package com.yuk.cspserver.content

data class ContentRequestDTO(val contentTypeId : Int,val name : String)

data class ContentResponseDTO(val id: String,val name : String, val contentTypeId: Int)