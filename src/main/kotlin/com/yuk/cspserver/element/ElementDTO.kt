package com.yuk.cspserver.element

import com.yuk.cspserver.element.file.File

data class ElementRequestDTO(val contentId : String, val elementTypeId : Int, val list: List<File>)

data class ElementResponseDTO(val id : Int)