package com.yuk.cspserver.common

internal fun String.toIntCheck() =
        this.toIntOrNull() ?: throw BadRequestException("input data must be positive integer")