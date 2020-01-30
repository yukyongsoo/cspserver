package com.yuk.cspserver.storage

import com.fasterxml.jackson.annotation.JsonIgnore
import com.yuk.cspserver.storage.strategy.DiskStrategy

data class StorageDTO(val id: Int,
                      val name: String,
                      val path: String,
                      val type : StorageType,
                      val usable: Boolean,
                      @JsonIgnore
                      val strategy: DiskStrategy)

data class StorageRequestDto(
        val name: String,
        val path: String,
        val type : StorageType
)