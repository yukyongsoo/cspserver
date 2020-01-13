package com.yuk.cspserver.archive

import com.yuk.cspserver.archive.archivestorage.ArchiveStorageQueryDAO
import com.yuk.cspserver.storage.StorageDTO
import com.yuk.cspserver.storage.StorageService
import org.springframework.stereotype.Service

@Service
class ArchiveService(private val archiveQueryDAO: ArchiveQueryDAO,
                     private val storageService: StorageService,
                     private val archiveStorageQueryDAO: ArchiveStorageQueryDAO) {

    suspend fun getAllArchive() = archiveQueryDAO.getAllArchive()

    suspend fun getArchive(archiveId: Int) : ArchiveDTO {
        return archiveQueryDAO.getArchive(archiveId) ?: throw IllegalStateException("archive $archiveId is not found")
    }

    suspend fun getUsableStorage(archiveId: Int): StorageDTO {
        val assignedList = archiveStorageQueryDAO.getStorageList(archiveId)
        val storageList = storageService.findStorageList(assignedList.map { it.storageId })
        return storageList.firstOrNull { it.usable } ?: throw IllegalStateException("can't find any usable storage in archive . $archiveId")
    }
}