package com.yuk.cspserver.archive

import com.yuk.cspserver.storage.Storage
import org.springframework.stereotype.Component

@Component
class ArchiveManager(private val archiveService: ArchiveService){
    private var archiveStorageMap = mapOf<String,List<Storage>>()

    fun setArchiveStorage(storageList: List<Storage>) {
        archiveStorageMap = storageList.groupBy { it.archiveName }
    }


}