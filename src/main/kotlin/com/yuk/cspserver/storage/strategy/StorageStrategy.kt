package com.yuk.cspserver.storage.strategy

import com.yuk.cspserver.element.file.filepart.ElementFile

abstract class StorageStrategy {
    abstract suspend fun saveFile(elementId : Int, storagePath : String, contentId : String, elementFile: ElementFile): StoragePath
    protected abstract fun createFilePath(contentId: String, elementId: Int, name: String): String
}