plugins {
    kotlin("jvm") version "2.3.0"
    id("io.ktor.plugin") version "3.0.0"
    kotlin("plugin.serialization") version "2.3.0"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    // Kotlin Coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.8.0")

    // Ktor Server Core
    implementation("io.ktor:ktor-server-core-jvm:3.0.0")
    implementation("io.ktor:ktor-server-netty-jvm:3.0.0")

    // Content Negotiation and Serialization
    implementation("io.ktor:ktor-server-content-negotiation-jvm:3.0.0")
    implementation("io.ktor:ktor-serialization-kotlinx-json-jvm:3.0.0")

    // Authentication and JWT
    implementation("io.ktor:ktor-server-auth-jvm:3.0.0")
    implementation("io.ktor:ktor-server-auth-jwt-jvm:3.0.0")
    implementation("com.auth0:java-jwt:4.4.0")

    // CORS
    implementation("io.ktor:ktor-server-cors-jvm:3.0.0")

    // Logging
    implementation("ch.qos.logback:logback-classic:1.5.6")

    // Call Logging
    implementation("io.ktor:ktor-server-call-logging-jvm:3.0.0")

    // Status Pages
    implementation("io.ktor:ktor-server-status-pages-jvm:3.0.0")

    // Exposed ORM + PostgreSQL
    implementation("org.jetbrains.exposed:exposed-core:0.55.0")
    implementation("org.jetbrains.exposed:exposed-dao:0.55.0")
    implementation("org.jetbrains.exposed:exposed-jdbc:0.55.0")
    implementation("org.jetbrains.exposed:exposed-java-time:0.55.0")
    implementation("org.postgresql:postgresql:42.7.4")

    // HikariCP - connection pool
    implementation("com.zaxxer:HikariCP:6.0.0")

    // H2 Database (для разработки и тестирования)
    implementation("com.h2database:h2:2.2.220")

    // BCrypt for password hashing
    implementation("at.favre.lib:bcrypt:0.10.2")

    // HTTP Client for Nobel Prize API calls
    implementation("io.ktor:ktor-client-core:3.0.0")
    implementation("io.ktor:ktor-client-cio:3.0.0")

    // Testing
    testImplementation(kotlin("test"))
    testImplementation("io.ktor:ktor-server-test-host:3.0.0")
}

kotlin {
    jvmToolchain(21)
}

tasks.test {
    useJUnitPlatform()
}

