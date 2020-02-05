package com.yuk.cspserver.archive

import com.yuk.cspserver.archive.archivestorage.ArchiveStorageComponent
import com.yuk.cspserver.common.BadRequestException
import com.yuk.cspserver.common.BadStateException
import com.yuk.cspserver.storage.StorageDTO
import com.yuk.cspserver.storage.StorageService
import org.springframework.stereotype.Service

@Service
class ArchiveService(private val archiveQueryDAO: ArchiveQueryDAO,
                     private val archiveCommandDAO: ArchiveCommandDAO,
                     private val storageService: StorageService,
                     private val archiveStorageComponent: ArchiveStorageComponent) {

    suspend fun getAllArchive() = archiveQueryDAO.getAllArchive()

    suspend fun getArchive(archiveId: Int): ArchiveDetailDTO {
        val archive = archiveQueryDAO.getArchive(archiveId)
                ?: throw BadStateException("archive $archiveId is not found")
        val storageIdList = archiveStorageComponent.findByArchiveId(archiveId).map { it.storageId }
        val storageList = storageService.findStorageList(storageIdList)
        return ArchiveDetailDTO(archive, storageList)
    }

    suspend fun getUsableStorage(archiveId: Int): StorageDTO {
        val assignedList = archiveStorageComponent.findByArchiveId(archiveId)
        val storageList = storageService.findStorageList(assignedList.map { it.storageId })

        return storageList.firstOrNull { it.usable }
                ?: throw BadStateException("can't find any usable storage in archive . $archiveId")
    }

    suspend fun deleteArchive(archiveId: Int) {
        if (archiveStorageComponent.findByArchiveId(archiveId).isNotEmpty())
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
        getArchive(archiveId)
        storageService.getStorage(storageId)

        archiveStorageComponent.addArchiveStorage(archiveId,storageId)
    }

    suspend fun deleteArchiveStorage(archiveId: Int, storageId: Int) {
        //TODO :: child element Check
        archiveStorageComponent.deleteArchiveStorage(archiveId,storageId)
    }
}