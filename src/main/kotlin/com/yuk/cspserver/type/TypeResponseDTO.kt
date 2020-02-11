package com.yuk.cspserver.type

data class TypeResponseDTO(val id: Int, val name: String, val category: TypeCategory)

data class TypeDetailResponseDTO(val id: Int, val name: String, val category: TypeCategory)

data class TypeRequestDTO(val name : String, val category: TypeCategory)
