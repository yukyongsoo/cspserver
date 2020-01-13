package com.yuk.cspserver.storage.strategy

import com.yuk.cspserver.element.file.filepart.ElementFile

interface StorageStrategy {
    fun saveFile(elementFile: ElementFile): StoragePath
}