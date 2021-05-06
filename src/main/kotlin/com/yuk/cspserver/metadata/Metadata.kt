package com.yuk.cspserver.metadata

interface Metadata<T> {
    fun getName() : String
    fun getValue() : T
    fun getType() : String
}