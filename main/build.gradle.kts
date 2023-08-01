plugins {
    application
}


dependencies {
    implementation(project(":domain"))
    implementation(project(":http-server"))
    implementation(project(":consumer"))
    implementation(project(":kafka"))
    implementation("org.springframework.boot:spring-boot-starter-actuator")
}

tasks {
    application {
        mainClass.set("io.quickstart.ApplicationKt")
    }
}