package com.yuk.cspserver.element.file

data class ElementFileDTO(val elementId: Int,
                          val rootPath: String,
                          val archivePath: String,
                          val filePath: String)