package com.yuk.cspserver.archive

import com.yuk.cspserver.common.BadRequestException
import com.yuk.cspserver.common.toIntCheck
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.*

@Component
class ArchiveHandler(private val archiveService: ArchiveService) {
    suspend fun getAllArchive(serverRequest: ServerRequest): ServerResponse {
        return ServerResponse.ok().bodyValueAndAwait(archiveService.getAllArchive())
    }

    suspend fun getArchive(serverRequest: ServerRequest): ServerResponse {
        val archiveId = serverRequest.pathVariable("archiveId").toIntCheck()
        return ServerResponse.ok().bodyValueAndAwait(archiveService.getArchive(archiveId))
    }

    suspend fun addArchive(serverRequest: ServerRequest): ServerResponse {
        val archiveDTO = serverRequest.awaitBodyOrNull<ArchiveRequestDTO>()
                ?: throw BadRequestException("archive request data not found. check you body data")
        archiveService.addArchive(archiveDTO)
        return ServerResponse.ok().buildAndAwait()
    }

    suspend fun deleteArchive(serverRequest: ServerRequest): ServerResponse {
        val archiveId = serverRequest.pathVariable("archiveId").toIntCheck()
        archiveService.deleteArchive(archiveId)
        return ServerResponse.ok().buildAndAwait()
    }

    suspend fun addArchiveStorage(serverRequest: ServerRequest): ServerResponse {
        val archiveId = serverRequest.pathVariable("archiveId").toIntCheck()
        val storageId = serverRequest.pathVariable("storageId").toIntCheck()
        archiveService.addArchiveStorage(archiveId,storageId)
        return ServerResponse.ok().buildAndAwait()

    }

    suspend fun deleteArchiveStorage(serverRequest: ServerRequest): ServerResponse {
        val archiveId = serverRequest.pathVariable("archiveId").toIntCheck()
        val storageId = serverRequest.pathVariable("storageId").toIntCheck()
        archiveService.deleteArchiveStorage(archiveId,storageId)
        return ServerResponse.ok().buildAndAwait()

    }
}