package com.yuk.cspserver.integration

import com.yuk.cspserver.content.ContentRequestDTO
import com.yuk.cspserver.content.ContentResponseDTO
import com.yuk.cspserver.element.ElementResponseDTO
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.TestConstructor
import org.springframework.test.context.jdbc.Sql
import org.springframework.test.web.reactive.server.WebTestClient

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
@Sql("/content.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
class ContentIntegrationTest(private val webTestClient: WebTestClient,
                             private val integrationTestUtil: IntegrationTestUtil) {
    private val contentId = 1
    private val elementTypeId = 1

    @Test
    fun `컨텐츠 만들기`() {
        val testContentRequestDTO = ContentRequestDTO(elementTypeId, "테스트")
        webTestClient.post()
                .uri("/content")
                .bodyValue(testContentRequestDTO)
                .exchange()
                .expectStatus().is2xxSuccessful
                .expectHeader().exists("location")
    }

    @Test
    fun `컨텐츠 정보 가져오기`() {
        webTestClient.get()
                .uri("/content/$contentId")
                .exchange()
                .expectStatus().is2xxSuccessful
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody(ContentResponseDTO::class.java)
    }

    @Test
    fun `엘리먼트 만들기`() {
        integrationTestUtil.createContentElement(contentId, elementTypeId)
    }

    @Test
    fun `엘리먼트 정보 가져오기`() {
        val elementId = integrationTestUtil.createContentElement(contentId, elementTypeId)

        webTestClient.head()
                .uri("/content/$contentId/$elementId")
                .exchange()
                .expectStatus().is2xxSuccessful
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody(ElementResponseDTO::class.java)
    }

    @Test
    fun `엘리먼트 파일 가져오기`() {
        val elementId = integrationTestUtil.createContentElement(contentId, elementTypeId)

        webTestClient.get()
                .uri("/content/$contentId/$elementId")
                .exchange()
                .expectStatus().is2xxSuccessful
                .expectBody(String::class.java)
                .returnResult().responseBody
    }


    @Test
    fun `컨텐츠 삭제`(){
        webTestClient.delete()
                .uri("/content/$contentId")
                .exchange()
                .expectStatus().is2xxSuccessful
    }

    @Test
    fun `엘리먼트 삭제 및 컨텐츠 폴더 정리`(){
        val elementId = integrationTestUtil.createContentElement(contentId, elementTypeId)

        webTestClient.delete()
                .uri("/content/$contentId/$elementId")
                .exchange()
                .expectStatus().is2xxSuccessful
    }

    @Test
    fun `엘리먼트 삭제`() {
        val elementId = integrationTestUtil.createContentElement(contentId, elementTypeId)
        integrationTestUtil.createContentElement(contentId, elementTypeId)

        webTestClient.delete()
                .uri("/content/$contentId/$elementId")
                .exchange()
                .expectStatus().is2xxSuccessful
    }
}