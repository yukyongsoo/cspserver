package com.yuk.cspserver.integration

import com.yuk.cspserver.archive.ArchiveRequestDTO
import com.yuk.cspserver.common.BadStateException
import com.yuk.cspserver.storage.StorageRequestDto
import com.yuk.cspserver.storage.StorageType
import com.yuk.cspserver.type.TypeCategory
import com.yuk.cspserver.type.TypeRequestDTO
import org.springframework.context.annotation.Lazy
import org.springframework.core.io.ClassPathResource
import org.springframework.http.MediaType
import org.springframework.http.client.MultipartBodyBuilder
import org.springframework.stereotype.Component
import org.springframework.test.web.reactive.server.WebTestClient

@Component
class IntegrationTestUtil(@Lazy private val webTestClient: WebTestClient) {

    fun addArchive(): Int {
        val request = ArchiveRequestDTO("test2")

        return webTestClient.post().uri("/archive")
                .accept(MediaType.APPLICATION_JSON)
                .bodyValue(request)
                .exchange()
                .expectStatus().is2xxSuccessful
                .expectBody(Int::class.java)
                .returnResult().responseBody!!
    }

    fun addStorage(): Int {
        val storageDTO = StorageRequestDto("junitTest", "/asdf", StorageType.DISK)

        return webTestClient.post().uri("/storage")
                .bodyValue(storageDTO)
                .exchange()
                .expectStatus().is2xxSuccessful
                .expectBody(Int::class.java)
                .returnResult().responseBody!!
    }

    fun createContentElement(contentId: Int, elementTypeId: Int): String {
        val body = MultipartBodyBuilder()
        body.part("file", ClassPathResource("/testFile.txt"))

        return webTestClient.post()
                .uri {
                    it.path("/content/$contentId")
                    it.queryParam("elementTypeId", elementTypeId)
                    it.build()
                }
                .bodyValue(body.build())
                .exchange()
                .expectStatus().is2xxSuccessful
                .returnResult<String>(String::class.java)
                .responseBody.blockFirst() ?: throw BadStateException("making test element fail")
    }

    fun addType(): Int {
        val request = TypeRequestDTO("asdf", TypeCategory.ELEMENT)

        return webTestClient.post().uri("/type")
                .bodyValue(request)
                .exchange()
                .expectStatus().is2xxSuccessful
                .expectBody(Int::class.java)
                .returnResult().responseBody!!
    }
}