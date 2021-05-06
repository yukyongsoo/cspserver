package com.yuk.cspserver.metadata.type

enum class MetadataClass(private val classId : Int) {
    BOOLEAN(1),
    FLOAT(2),
    STRING(3),
    INTEGER(4)
}