package io.quickstart.http.infra

import org.springdoc.core.models.GroupedOpenApi
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.config.EnableWebFlux


@Configuration
@EnableWebFlux
class SwaggerConfig {
    @Bean
    fun api(): GroupedOpenApi = GroupedOpenApi.builder()
        .group("quickstart")
        .pathsToMatch("/**")
        .build()
}