package com.yuk.cspserver

import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.core.ReactiveStringRedisTemplate
import org.springframework.kafka.core.KafkaTemplate
import javax.annotation.PostConstruct

@Configuration
class TempConfig(private val reactiveStringRedisTemplate: ReactiveStringRedisTemplate,
                 private val kafkaTemplate: KafkaTemplate<String, String>) {
    @PostConstruct
    fun testForRedis() {
        reactiveStringRedisTemplate.keys("")
        kafkaTemplate.flush()
    }
}