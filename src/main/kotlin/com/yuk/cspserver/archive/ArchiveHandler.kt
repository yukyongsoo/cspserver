package com.yuk.cspserver.archive

import com.yuk.cspserver.common.BadRequestException
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.*

@Component
class ArchiveHandler(private val archiveService: ArchiveService) {
    suspend fun getAllArchive(serverRequest: ServerRequest): ServerResponse {
        return ServerResponse.ok().bodyValueAndAwait(archiveService.getAllArchive())
    }

    suspend fun addArchive(serverRequest: ServerRequest): ServerResponse {
        val archiveDTO = serverRequest.awaitBodyOrNull<ArchiveRequestDTO>()
                ?: throw BadRequestException("archive request data not found. check you body data")
        archiveService.addArchive(archiveDTO)
        return ServerResponse.ok().buildAndAwait()
    }

    suspend fun deleteArchive(serverRequest: ServerRequest): ServerResponse {
        val archiveId = serverRequest.pathVariable("archiveId").toInt()
        archiveService.deleteArchive(archiveId)
        return ServerResponse.ok().buildAndAwait()
    }

    suspend fun addArchiveStorage(serverRequest: ServerRequest): ServerResponse {
        val archiveId = serverRequest.pathVariable("archiveId").toInt()
        val storageId = serverRequest.pathVariable("storageId").toInt()
        archiveService.addArchiveStorage(archiveId,storageId)
        return ServerResponse.ok().buildAndAwait()

    }

    suspend fun deleteArchiveStorage(serverRequest: ServerRequest): ServerResponse {
        val archiveId = serverRequest.pathVariable("archiveId").toInt()
        val storageId = serverRequest.pathVariable("storageId").toInt()
        archiveService.deleteArchiveStorage(archiveId,storageId)
        return ServerResponse.ok().buildAndAwait()

    }
}