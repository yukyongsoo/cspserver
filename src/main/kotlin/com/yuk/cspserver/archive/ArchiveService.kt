package com.yuk.cspserver.archive

import com.yuk.cspserver.archive.common.ArchiveQueryDAO
import com.yuk.cspserver.element.file.filepart.ElementFile
import com.yuk.cspserver.storage.StorageService
import org.springframework.stereotype.Service
import javax.annotation.PostConstruct

@Service
class ArchiveService(private val archiveManager: ArchiveManager,
                     private val archiveQueryDAO: ArchiveQueryDAO,
                     private val storageService: StorageService) {
    @PostConstruct
    fun cachingArchiveStorage(){
        val storageList = storageService.getAllStorage()
        archiveManager.setArchiveStorage(storageList)
    }

    suspend fun getAllArchive() = archiveQueryDAO.getAllArchive()

    suspend fun saveFile(archiveId: Int, elementId: Int, elementFile: ElementFile) {

    }
}