package com.yuk.cspserver.metadata

import org.springframework.stereotype.Service

@Service
class MetadataService(private val metadataCommandDAO: MetadataCommandDAO,
                      private val metadataQueryDAO: MetadataQueryDAO) {

}