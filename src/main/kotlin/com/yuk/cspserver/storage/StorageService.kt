package com.yuk.cspserver.storage

import org.springframework.stereotype.Service

@Service
class StorageService(private val storageCommandDAO: StorageCommandDAO,
                     private val storageQueryDAO: StorageQueryDAO) {

    suspend fun findStorageList(storageIdList: List<Int>)
        = storageQueryDAO.findStorages(storageIdList)
}