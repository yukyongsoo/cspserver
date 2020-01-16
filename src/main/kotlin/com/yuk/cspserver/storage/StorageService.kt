package com.yuk.cspserver.storage

import com.yuk.cspserver.storage.strategy.DiskStrategy
import org.springframework.stereotype.Service

@Service
class StorageService(private val storageCommandDAO: StorageCommandDAO,
                     private val storageQueryDAO: StorageQueryDAO) {

    suspend fun findStorageList(storageIdList: List<Int>) =
            storageQueryDAO.findStorages(storageIdList)
                    .map {
                        getStorageDto(it)
                    }

    suspend fun getStorage(storageId: Int) =
            storageQueryDAO.getStorage(storageId)?.run {
                getStorageDto(this)
            } ?: throw IllegalStateException("can't find any storage for $storageId")

    private fun getStorageDto(storageEntity: StorageEntity) =
            when (storageEntity.type) {
                1 -> StorageDTO(storageEntity.id, storageEntity.name, storageEntity.path, storageEntity.usable, DiskStrategy())
                2 -> StorageDTO(storageEntity.id, storageEntity.name, storageEntity.path, storageEntity.usable, DiskStrategy())
                else ->
                    throw IllegalStateException("can't find storage Strategy for type ${storageEntity.type} for storage. storage Id is ${storageEntity.id}")
            }
}