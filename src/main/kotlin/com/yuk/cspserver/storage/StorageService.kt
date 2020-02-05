package com.yuk.cspserver.storage

import com.yuk.cspserver.archive.archivestorage.ArchiveStorageComponent
import com.yuk.cspserver.common.BadRequestException
import com.yuk.cspserver.common.BadStateException
import com.yuk.cspserver.storage.strategy.DiskStrategy
import org.springframework.stereotype.Service

@Service
class StorageService(private val storageCommandDAO: StorageCommandDAO,
                     private val storageQueryDAO: StorageQueryDAO,
                     private val archiveStorageComponent: ArchiveStorageComponent) {

    suspend fun findStorageList(storageIdList: List<Int>) =
            storageQueryDAO.findStorages(storageIdList)
                    .map {
                        getStorageDto(it)
                    }

    suspend fun getStorage(storageId: Int) =
            storageQueryDAO.getStorage(storageId)?.run {
                getStorageDto(this)
            } ?: throw BadStateException("can't find any storage for $storageId")


    suspend fun getAllStorage() =
            storageQueryDAO.getAllStorage().map {
                getStorageDto(it)
            }

    suspend fun deleteStorage(storageId: String) {
        archiveStorageComponent.findByStorageId(storageId)?.run{
            throw BadRequestException("current delete target storage contain by archive. archiveId is ${this.archiveId}")
        }
        //TODO :: check element
        storageCommandDAO.deleteStorage(storageId)
    }

    suspend fun addStorage(storageRequest: StorageRequestDto) {
        storageCommandDAO.addStorage(storageRequest.name, storageRequest.path, storageRequest.type)
    }

    private fun getStorageDto(storageReadEntity: StorageReadEntity) =
            when (storageReadEntity.type) {
                1 -> StorageDTO(storageReadEntity.id, storageReadEntity.name, storageReadEntity.path, StorageType.DISK, storageReadEntity.usable, DiskStrategy())
                2 -> StorageDTO(storageReadEntity.id, storageReadEntity.name, storageReadEntity.path, StorageType.S3, storageReadEntity.usable, DiskStrategy())
                else ->
                    throw BadStateException("can't find storage Strategy for type ${storageReadEntity.type} for storage. storage Id is ${storageReadEntity.id}")
            }
}