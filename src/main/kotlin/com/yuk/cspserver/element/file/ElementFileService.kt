package com.yuk.cspserver.element.file

import com.yuk.cspserver.archive.ArchiveService
import com.yuk.cspserver.element.ElementRequestDTO
import com.yuk.cspserver.element.file.ElementFileCommandDAO
import com.yuk.cspserver.element.file.ElementFileQueryDAO
import com.yuk.cspserver.element.file.filepart.ElementFile
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Service

@Service
class ElementFileService(private val elementFileQueryDAO: ElementFileQueryDAO,
                         private val elementFileCommandDAO: ElementFileCommandDAO,
                         private val archiveService: ArchiveService) {
    suspend fun saveFile(archiveId: Int, elementId: Int, element: ElementRequestDTO) {
        val storage = archiveService.getUsableStorage(archiveId)
        val path = storage.strategy.saveFile(elementId, storage.path, element.contentId, element.elementFile)
        elementFileCommandDAO.saveFile(archiveId, elementId, path)
    }

    suspend fun getFile(elementId: String) {
        val elementFile = elementFileQueryDAO.getElementFile(elementId)
        if (elementFile.isEmpty())
            throw IllegalStateException("can't find any file for element. elementId is $elementId")
        elementFile.map {
            //TODO :: storagePath to storageId
        }



    }


}