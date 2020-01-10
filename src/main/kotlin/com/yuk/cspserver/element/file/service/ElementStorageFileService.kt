package com.yuk.cspserver.element.file.service

import com.yuk.cspserver.element.file.ElementFileCommandDAO
import com.yuk.cspserver.element.file.ElementFileQueryDAO
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Service

@Service
@Qualifier("storageFileService")
class ElementStorageFileService(private val elementFileQueryDAO: ElementFileQueryDAO,
                                private val elementFileCommandDAO: ElementFileCommandDAO) : ElementFileService{






}