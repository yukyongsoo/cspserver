package com.yuk.cspserver.storage.strategy

import com.yuk.cspserver.element.file.filepart.ElementFile
import com.yuk.cspserver.element.file.filepart.ElementFilePart
import java.nio.file.Files
import java.nio.file.Paths

class DiskStrategy : StorageStrategy() {
    override suspend fun saveFile(elementId: Int, storagePath: String, contentId: String, elementFile: ElementFile): StoragePath {
        val filePath = createFilePath(contentId, elementId)
        val storage = StoragePath(storagePath, filePath)
        makeFolder(storagePath, contentId)
        elementFile.transfer("$storagePath/$filePath")
        return storage
    }

    override suspend fun getFile(elementId: Int, storagePath: String, contentId: String): ElementFile {
        val filePath = createFilePath(contentId, elementId)
        //TODO :: load from file part for disk
        return ElementFilePart()
    }

    override fun createFilePath(contentId: String, elementId: Int) =
            "$contentId/$elementId"

    override fun makeFolder(storagePath: String, contentId: String) {
        Files.createDirectories(Paths.get("$storagePath/$contentId"))
    }
}