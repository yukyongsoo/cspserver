package com.yuk.cspserver.element.file

import com.yuk.cspserver.archive.ArchiveService
import com.yuk.cspserver.element.file.ElementFileCommandDAO
import com.yuk.cspserver.element.file.ElementFileQueryDAO
import com.yuk.cspserver.element.file.filepart.ElementFile
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Service

@Service
class ElementFileService(private val elementFileQueryDAO: ElementFileQueryDAO,
                         private val elementFileCommandDAO: ElementFileCommandDAO,
                         private val archiveService: ArchiveService){
    suspend fun saveFile(archiveId: Int, elementId: Int, elementFile: ElementFile) {
        val storage = archiveService.getUsableStorage(archiveId)
        val path = storage.strategy.saveFile(elementFile)
        elementFileCommandDAO.saveFile(archiveId,elementId,path)
    }


}