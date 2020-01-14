package com.yuk.cspserver.storage

import com.yuk.cspserver.storage.strategy.DiskStrategy
import org.springframework.stereotype.Service

@Service
class StorageService(private val storageCommandDAO: StorageCommandDAO,
                     private val storageQueryDAO: StorageQueryDAO) {

    suspend fun findStorageList(storageIdList: List<Int>) =
            storageQueryDAO.findStorages(storageIdList)
                    .map {
                        when (it.type) {
                            1 -> StorageDTO(it.id, it.name, it.path, it.usable, DiskStrategy())
                            2 -> StorageDTO(it.id, it.name, it.path, it.usable, DiskStrategy())
                            else ->
                                throw IllegalStateException("can't find storage Strategy for type ${it.type} for storage. storage Id is ${it.id}")
                        }
                    }

}