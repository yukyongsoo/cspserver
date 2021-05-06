package com.yuk.cspserver.storage.strategy

import com.yuk.cspserver.element.file.filepart.ElementFileReader
import com.yuk.cspserver.element.file.filepart.ElementFileWriter

abstract class StorageStrategy {
    abstract suspend fun saveFile(elementId: Int, storagePath: String, contentId: String, elementFileWriter: ElementFileWriter)
            : StoragePath
    abstract suspend fun getFile(elementId : Int, storagePath: String,contentId: String) : ElementFileReader
    abstract suspend fun deleteFile(elementId: Int, storagePath: String, contentId: String)

    protected abstract fun createFilePath(contentId: String, elementId: Int): String
    protected abstract fun makeFolder(storagePath: String, contentId: String)
    protected abstract fun cleanUpContentFolder(storagePath: String, contentId: String)
}