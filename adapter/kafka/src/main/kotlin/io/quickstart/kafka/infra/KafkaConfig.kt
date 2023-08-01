package io.quickstart.kafka.infra

import com.fasterxml.jackson.databind.JsonSerializer
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer
import org.apache.kafka.clients.producer.ProducerConfig
import org.apache.kafka.common.serialization.StringSerializer
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.core.DefaultKafkaProducerFactory
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.core.ProducerFactory
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Configuration
class KafkaConfig(
    @Value("\${spring.kafka.bootstrap-servers}")
    private val bootstrapServers: String
) {

    @Bean
    fun producer(): ProducerFactory<String, Any> {
        val configProperties = mutableMapOf<String, Any>().apply {
            put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers)
            put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer::class.java)
            put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer::class.java)
        }
        return DefaultKafkaProducerFactory(configProperties)
    }

    @Bean
    fun template() =
        KafkaTemplate(producer())

    @Bean
    fun jackson2ObjectMapperBuilderCustomizer(): Jackson2ObjectMapperBuilderCustomizer =
        Jackson2ObjectMapperBuilderCustomizer { builder ->
            builder.modules(JavaTimeModule().apply {
                addSerializer(LocalDateTime::class.java, LocalDateTimeSerializer(DateTimeFormatter.ISO_DATE_TIME))
                addSerializer(LocalDate::class.java, LocalDateSerializer(DateTimeFormatter.ISO_DATE))
            })
            builder.featuresToDisable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
        }

    @Bean
    fun objectMapper(): ObjectMapper {
        return ObjectMapper()
    }
}