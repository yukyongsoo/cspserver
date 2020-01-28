package com.yuk.cspserver.archive.archivestorage

import com.yuk.cspserver.common.BadRequestException
import org.springframework.stereotype.Component

@Component
class ArchiveStorageComponent(private val archiveStorageQueryDAO: ArchiveStorageQueryDAO,
                              private val archiveStorageCommandDAO: ArchiveStorageCommandDAO) {
    suspend fun getStorageList(archiveId: Int) =
            archiveStorageQueryDAO.getStorageList(archiveId)

    suspend fun findByStorageId(storageId: String) =
            archiveStorageQueryDAO.findByStorageId(storageId)

    suspend fun findByArchiveIdAndStorageId(archiveId: Int, storageId: Int) =
            archiveStorageQueryDAO.findByArchiveIdAndStorageId(archiveId, storageId)

    suspend fun addArchiveStorage(archiveId: Int, storageId: Int) {
        findByArchiveIdAndStorageId(archiveId, storageId)?.run {
            throw BadRequestException("storage already contain for archive. archiveId : $archiveId , storageId : $storageId")
        }

        archiveStorageCommandDAO.addArchiveStorage(archiveId, storageId)
    }

    suspend fun deleteArchiveStorage(archiveId: Int, storageId: Int) {


        archiveStorageCommandDAO.deleteArchiveStorage(archiveId,storageId)
    }

}