package com.yuk.cspserver.archive

import com.yuk.cspserver.storage.StorageService
import org.springframework.stereotype.Service
import javax.annotation.PostConstruct

@Service
class ArchiveService(private val archiveManager: ArchiveManager,
                     private val archiveDAO: ArchiveDAO,
                     private val storageService: StorageService) {
    @PostConstruct
    fun cachingArchiveStorage(){
        val storageList = storageService.getAllStorage()
        archiveManager.setArchiveStorage(storageList)
    }

    suspend fun getAllArchive() = archiveDAO.getAllArchive()
}