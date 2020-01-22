package com.yuk.cspserver.common

open class CommonCspException : RuntimeException {
    constructor(message: String, cause: Throwable) : super(message,cause)
    constructor(message: String) : super(message)
}

class ChildFoundException(message: String) : CommonCspException(message)

class AuthException : CommonCspException{
    constructor(message: String, cause: Throwable) : super(message,cause)
    constructor(message: String) : super(message)
}
