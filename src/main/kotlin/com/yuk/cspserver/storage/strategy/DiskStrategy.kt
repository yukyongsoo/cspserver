package com.yuk.cspserver.storage.strategy

import com.yuk.cspserver.element.file.filepart.ElementFile

class DiskStrategy : StorageStrategy {
    override fun saveFile(elementFile: ElementFile): StoragePath {
        //TODO :: save file async
    }
}