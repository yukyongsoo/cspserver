package com.yuk.cspserver.integration

import com.yuk.cspserver.type.TypeDetailResponseDTO
import com.yuk.cspserver.type.TypeResponseDTO
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.TestConstructor
import org.springframework.test.context.jdbc.Sql
import org.springframework.test.web.reactive.server.WebTestClient

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
class TypeIntegrationTest(private val webTestClient: WebTestClient,
                          private val integrationTestUtil: IntegrationTestUtil) {
    @Test
    fun `타입 목록 가져오기`() {
        webTestClient.get().uri("/type")
                .exchange()
                .expectStatus().is2xxSuccessful
                .expectBodyList(TypeResponseDTO::class.java)
    }

    @Test
    @Sql("/type.sql")
    fun `타입 상세 가져오기`(){
        val typeId = integrationTestUtil.addType()

        webTestClient.get().uri("/type/$typeId")
                .exchange()
                .expectStatus().is2xxSuccessful
                .expectBody(TypeDetailResponseDTO::class.java)
    }

    @Test
    @Sql("/type.sql")
    fun `타입 추가하기`(){
        integrationTestUtil.addType()
    }

    @Test
    @Sql("/type.sql")
    fun `타입 삭제하기`() {
        val typeId = integrationTestUtil.addType()

        webTestClient.delete().uri("/type/$typeId")
                .exchange()
                .expectStatus().is2xxSuccessful
    }


}