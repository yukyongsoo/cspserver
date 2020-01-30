package com.yuk.cspserver.common

internal fun String.toIntCheck() =
        this.toIntOrNull() ?: throw IllegalArgumentException("input data must be positive integer")