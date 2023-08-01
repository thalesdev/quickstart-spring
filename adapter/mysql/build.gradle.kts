val kotysaVersion: String by rootProject

dependencies {
    implementation(project(":domain"))

    implementation("org.ufoss.kotysa:kotysa-spring-r2dbc:$kotysaVersion")
    implementation("org.springframework:spring-r2dbc")
    implementation("org.springframework.data:spring-data-r2dbc")
    runtimeOnly("io.asyncer:r2dbc-mysql")
}