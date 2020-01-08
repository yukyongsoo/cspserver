package com.yuk.cspserver.archive

import com.yuk.cspserver.storage.StorageDTO
import org.springframework.stereotype.Component

@Component
class ArchiveManager{
    private var archiveStorageMap = mapOf<Int,List<StorageDTO>>()

    fun setArchiveStorage(storageList: List<StorageDTO>) {
        archiveStorageMap = storageList.groupBy { it.archiveId }
    }
}