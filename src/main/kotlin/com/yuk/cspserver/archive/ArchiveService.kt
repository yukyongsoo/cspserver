package com.yuk.cspserver.archive

import com.yuk.cspserver.archive.archivestorage.ArchiveStorageComponent
import com.yuk.cspserver.archive.archivestorage.ArchiveStorageQueryDAO
import com.yuk.cspserver.common.BadRequestException
import com.yuk.cspserver.storage.StorageDTO
import com.yuk.cspserver.storage.StorageService
import org.springframework.stereotype.Service

@Service
class ArchiveService(private val archiveQueryDAO: ArchiveQueryDAO,
                     private val archiveCommandDAO: ArchiveCommandDAO,
                     private val storageService: StorageService,
                     private val archiveStorageComponent: ArchiveStorageComponent) {

    suspend fun getAllArchive() = archiveQueryDAO.getAllArchive()

    suspend fun getArchive(archiveId: Int) : ArchiveDTO {
        return archiveQueryDAO.getArchive(archiveId) ?: throw IllegalStateException("archive $archiveId is not found")
    }

    suspend fun getUsableStorage(archiveId: Int): StorageDTO {
        val assignedList = archiveStorageComponent.getStorageList(archiveId)
        val storageList = storageService.findStorageList(assignedList.map { it.storageId })

        return storageList.firstOrNull { it.usable } ?: throw IllegalStateException("can't find any usable storage in archive . $archiveId")
    }

    suspend fun deleteArchive(archiveId: Int) {
        if(archiveStorageComponent.getStorageList(archiveId).isNotEmpty())
            throw BadRequestException("archive $archiveId has contain storage. please remove first")

        archiveCommandDAO.deleteArchive(archiveId)
    }

    suspend fun addArchive(archiveDTO: ArchiveRequestDTO) {
        archiveQueryDAO.findByName(archiveDTO.name)?.run {
            throw BadRequestException("archive name duplicated. name is ${archiveDTO.name}")
        }

        archiveCommandDAO.addArchive(archiveDTO.name)
    }

    suspend fun addArchiveStorage(archiveId: Int, storageId: Int) {
        archiveStorageComponent.addArchiveStorage(archiveId,storageId)
    }

    suspend fun deleteArchiveStorage(archiveId: Int, storageId: Int) {
        //TODO :: child element Check
        archiveStorageComponent.deleteArchiveStorage(archiveId,storageId)
    }
}