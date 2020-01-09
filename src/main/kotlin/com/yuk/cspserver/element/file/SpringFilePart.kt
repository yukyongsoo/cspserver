package com.yuk.cspserver.element.file

import org.springframework.http.codec.multipart.FilePart

data class SpringFilePart(val filePart: FilePart) : File