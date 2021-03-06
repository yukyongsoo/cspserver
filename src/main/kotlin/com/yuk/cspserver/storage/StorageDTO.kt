package com.yuk.cspserver.storage

import com.yuk.cspserver.storage.strategy.DiskStrategy

data class StorageDTO(val id: Int,
                      val name: String,
                      val path: String,
                      val type: StorageType,
                      val usable: Boolean,
                      val strategy: DiskStrategy)

data class StorageResponseDTO(val id: Int,
                              val name: String,
                              val path: String,
                              val type: StorageType,
                              val usable: Boolean)

data class StorageRequestDto(val name: String,
                             val path: String,
                             val type: StorageType)