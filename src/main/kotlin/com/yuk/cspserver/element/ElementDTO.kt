package com.yuk.cspserver.element

import com.yuk.cspserver.element.file.filepart.ElementFileWriter

data class ElementRequestDTO(val contentId: String, val elementTypeId: Int, val elementFileWriter: ElementFileWriter)

data class ElementResponseDTO(val id: Int, val contentId: String, val type: Int, val name: String)