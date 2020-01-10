package com.yuk.cspserver.element

import com.yuk.cspserver.element.file.filepart.ElementFile

data class ElementRequestDTO(val contentId : String, val elementTypeId : Int, val elementFile : ElementFile)

data class ElementResponseDTO(val id : Int)