package com.yuk.cspserver.element.file.filepart

interface ElementFileWriter {
    fun getName() : String
    suspend fun transfer(path : String)
}