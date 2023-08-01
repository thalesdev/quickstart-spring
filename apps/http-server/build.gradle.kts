val springDocVersion: String by rootProject

dependencies {
    implementation(project(":domain"))
    implementation(project(":mysql"))
    implementation("org.springframework.boot:spring-boot-starter-graphql")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("org.springframework.boot:spring-boot-starter-webflux")

    implementation("org.springdoc:springdoc-openapi-starter-webflux-ui:$springDocVersion")


}