package com.yuk.cspserver.integration

import com.yuk.cspserver.storage.StorageResponseDTO
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.TestConstructor
import org.springframework.test.web.reactive.server.WebTestClient

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
class StorageIntegrationTest(private val webTestClient: WebTestClient,
                             private val integrationTestUtil: IntegrationTestUtil) {

    @Test
    fun `스토리지 목록 가져오기`() {
        webTestClient.get().uri("/storage")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().is2xxSuccessful
                .expectBodyList(StorageResponseDTO::class.java)
    }

    @Test
    fun `스토리지 추가`() {
        integrationTestUtil.addStorage()
    }

    @Test
    fun `스토리지 삭제`() {
        val storageId = integrationTestUtil.addStorage()

        webTestClient.delete().uri("/storage/$storageId")
                .exchange()
                .expectStatus().is2xxSuccessful
    }


}