package com.yuk.cspserver.element.file

import com.yuk.cspserver.archive.ArchiveService
import com.yuk.cspserver.element.ElementRequestDTO
import com.yuk.cspserver.element.file.filepart.ElementFileReader
import com.yuk.cspserver.element.file.filepart.ElementFileWriter
import com.yuk.cspserver.storage.StorageService
import org.springframework.stereotype.Service

@Service
class ElementFileService(private val elementFileQueryDAO: ElementFileQueryDAO,
                         private val elementFileCommandDAO: ElementFileCommandDAO,
                         private val archiveService: ArchiveService,
                         private val storageService: StorageService) {
    suspend fun saveFile(archiveId: Int, elementId: Int, element: ElementRequestDTO) {
        val storage = archiveService.getUsableStorage(archiveId)
        val path = storage.strategy.saveFile(elementId, storage.path, element.contentId, element.elementFileWriter)
        elementFileCommandDAO.saveFile(archiveId, elementId, storage.id, path)
    }

    suspend fun getFile(elementId: Int, contentId: String): ElementFileReader {
        val elementFile = elementFileQueryDAO.getElementFile(elementId)
        if (elementFile.isEmpty())
            throw IllegalStateException("can't find any file for element. elementId is $elementId")
        elementFile.forEachIndexed { index, elementFileEntity ->
            val storage = storageService.getStorage(elementFileEntity.storageId)
            return storage.strategy.getFile(elementId, storage.path, contentId)
        }
        throw IllegalStateException("can't get any file from storage. check you storage Data. id is $elementId")
    }
}