package com.yuk.cspserver.integration

import com.yuk.cspserver.archive.ArchiveDTO
import com.yuk.cspserver.archive.ArchiveDetailDTO
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.TestConstructor
import org.springframework.test.context.jdbc.Sql
import org.springframework.test.web.reactive.server.WebTestClient

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
class ArchiveIntegrationTest(private val webTestClient: WebTestClient,
                             private val integrationTestUtil: IntegrationTestUtil) {

    @Test
    fun `아카이브 목록 가져오기`() {
        webTestClient.get().uri("/archive")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().is2xxSuccessful
                .expectBodyList(ArchiveDTO::class.java)
    }

    @Test
    @Sql("/archive.sql")
    fun `아카이브 생성`() {
        integrationTestUtil.addArchive()
    }

    @Test
    @Sql("/archive.sql")
    fun `아카이브 삭제`() {
        val archiveId = integrationTestUtil.addArchive()

        webTestClient.delete().uri("/archive/$archiveId")
                .exchange()
                .expectStatus().is2xxSuccessful
    }

    @Test
    @Sql("/archive.sql")
    fun `아카이브 스토리지 할당`() {
        attachArchiveStorage()
    }

    @Test
    fun `아카이브 상세 가져오기`() {
        webTestClient.get().uri("/archive/1")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().is2xxSuccessful
                .expectBodyList(ArchiveDetailDTO::class.java)
    }

    @Test
    @Sql("/archive.sql")
    fun `아카이브 스토리지 제거`() {
        val (archiveId, storageId) = attachArchiveStorage()

        webTestClient.delete().uri("/archive/$archiveId/$storageId")
                .exchange()
                .expectStatus().is2xxSuccessful
    }

    private fun attachArchiveStorage(): Pair<Int, Int> {
        val archiveId = integrationTestUtil.addArchive()
        val storageId = integrationTestUtil.addStorage()

        webTestClient.post().uri("/archive/$archiveId/$storageId")
                .exchange()
                .expectStatus().is2xxSuccessful

        return archiveId to storageId
    }
}