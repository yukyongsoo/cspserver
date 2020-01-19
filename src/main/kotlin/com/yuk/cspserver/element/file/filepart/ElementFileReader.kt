package com.yuk.cspserver.element.file.filepart

import org.springframework.core.io.FileSystemResource

interface ElementFileReader {
    fun getResource() : FileSystemResource
}