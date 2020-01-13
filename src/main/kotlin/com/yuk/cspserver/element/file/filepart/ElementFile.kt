package com.yuk.cspserver.element.file.filepart

interface ElementFile {
    fun getName() : String
    suspend fun transfer(path : String)
}