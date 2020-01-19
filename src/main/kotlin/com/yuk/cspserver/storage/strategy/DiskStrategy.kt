package com.yuk.cspserver.storage.strategy

import com.yuk.cspserver.element.file.filepart.ElementFileReader
import com.yuk.cspserver.element.file.filepart.ElementFileReaderPart
import com.yuk.cspserver.element.file.filepart.ElementFileWriter
import org.springframework.core.io.FileSystemResource
import java.io.File
import java.nio.file.Files
import java.nio.file.Paths

class DiskStrategy : StorageStrategy() {
    override suspend fun saveFile(elementId: Int, storagePath: String, contentId: String, elementFileWriter: ElementFileWriter): StoragePath {
        val filePath = createFilePath(contentId, elementId)
        val storage = StoragePath(storagePath, filePath)
        makeFolder(storagePath, contentId)
        elementFileWriter.transfer("$storagePath/$filePath")
        return storage
    }

    override suspend fun getFile(elementId: Int, storagePath: String, contentId: String): ElementFileReader {
        val filePath = createFilePath(contentId, elementId)
        val filePart = FileSystemResource(File("$storagePath/$filePath"))
        return ElementFileReaderPart(filePart)
    }

    override fun createFilePath(contentId: String, elementId: Int) =
            "$contentId/$elementId"

    override fun makeFolder(storagePath: String, contentId: String) {
        Files.createDirectories(Paths.get("$storagePath/$contentId"))
    }
}