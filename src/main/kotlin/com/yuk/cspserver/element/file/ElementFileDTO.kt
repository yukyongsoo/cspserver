package com.yuk.cspserver.element.file

data class ElementFileDTO(val elementId: Int,
                          val archiveId: Int,
                          val storageId : Int,
                          val storagePath: String,
                          val filePath: String)