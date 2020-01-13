package com.yuk.cspserver.storage.strategy

import com.yuk.cspserver.element.file.filepart.ElementFile

class DiskStrategy : StorageStrategy() {
    override suspend fun saveFile(elementId: Int, storagePath: String, contentId: String, elementFile: ElementFile): StoragePath {
        val filePath = createFilePath(contentId,elementId,elementFile.getName())
        val storage = StoragePath(storagePath,filePath)
        elementFile.transfer("$storagePath/$filePath")
        return storage
    }

    override fun createFilePath(contentId: String, elementId: Int, name: String) =
            "$contentId/$elementId/$name"
}