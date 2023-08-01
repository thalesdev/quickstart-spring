val springCloudVersion: String by project

plugins {
    kotlin("jvm") version "1.9.0"
    kotlin("plugin.spring") version "1.9.0"
    id("org.springframework.boot") version "3.1.1"
    id("io.spring.dependency-management") version "1.1.0"
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
}

allprojects {


    group = "io.quickstart"
    version = "0.0.1"

    repositories {
        mavenCentral()
        mavenLocal()
        maven { url = uri("https://jitpack.io") }
    }

    apply {
        plugin("java-library")
        plugin("org.jetbrains.kotlin.jvm")
        plugin("org.jetbrains.kotlin.plugin.spring")
        plugin("jacoco")
        plugin("org.springframework.boot")
        plugin("io.spring.dependency-management")
    }

    tasks.withType<Test> {
        useJUnitPlatform()
    }

    dependencies {
        annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")



        implementation("org.springframework:spring-tx")
        implementation("org.springframework.boot:spring-boot-autoconfigure")
        implementation("org.springframework.boot:spring-boot-starter-logging")

        implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
        implementation("com.fasterxml.jackson.datatype:jackson-datatype-jdk8")
        implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310")

        implementation(kotlin("reflect"))
        implementation(kotlin("stdlib-jdk8"))
        implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core")
        implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor")

        developmentOnly("org.springframework.boot:spring-boot-devtools")

        testImplementation("org.springframework.boot:spring-boot-starter-test")
        testImplementation("io.projectreactor:reactor-test")
    }

    dependencyManagement {
        imports {
            mavenBom("org.springframework.cloud:spring-cloud-dependencies:$springCloudVersion")
        }
    }

    sourceSets {
        main { java.setSrcDirs(listOf("src/main/kotlin")) }
        test { java.setSrcDirs(listOf("src/test/kotlin")) }
    }

    kotlin {
        sourceSets {
            main { kotlin.setSrcDirs(listOf("src/main/kotlin")) }
            test { kotlin.setSrcDirs(listOf("src/test/kotlin")) }
        }
    }

    configurations {
        compileOnly {
            extendsFrom(configurations.annotationProcessor.get())
        }
    }
}




