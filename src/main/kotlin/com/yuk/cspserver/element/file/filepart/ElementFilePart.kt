package com.yuk.cspserver.element.file.filepart

import com.yuk.cspserver.element.file.filepart.ElementFile
import org.springframework.http.codec.multipart.FilePart

data class ElementFilePart(val filePart: FilePart) : ElementFile {
    override fun getName() =
        filePart.filename()
}