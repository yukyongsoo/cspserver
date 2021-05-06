package com.yuk.cspserver.element.file.filepart

import org.springframework.core.io.FileSystemResource

class ElementFileReaderPart(private val filePart: FileSystemResource) : ElementFileReader {
    override fun getResource() = filePart
}