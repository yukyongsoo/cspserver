package com.yuk.cspserver.element.file.filepart

import kotlinx.coroutines.reactive.awaitFirstOrNull
import org.springframework.http.codec.multipart.FilePart
import java.io.File

data class ElementFilePart(val filePart: FilePart) : ElementFile {
    override fun getName() =
        filePart.filename()

    override suspend fun transfer(path: String) {
        filePart.transferTo(File(path)).awaitFirstOrNull()
    }
}