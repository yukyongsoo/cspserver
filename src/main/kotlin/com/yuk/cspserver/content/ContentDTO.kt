package com.yuk.cspserver.content

import com.yuk.cspserver.content.type.ContentTypeDTO
import org.springframework.http.codec.multipart.FilePart


data class ContentRequestDTO(val contentTypeDTO: ContentTypeDTO, val fileParts: List<FilePart>)

data class ContentResponseDTO(val id: String)