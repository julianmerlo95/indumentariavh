val ktor_version: String by project
val kotlin_version: String by project
val logback_version: String by project

val exposedVersion = "0.38.2"

plugins {
    application
    kotlin("jvm") version "1.7.10"
    kotlin("plugin.serialization").version("1.6.21")
}

group = "com.backend"
version = "0.0.1"
application {
    mainClass.set("com.backend.ApplicationKt")

    val isDevelopment: Boolean = project.ext.has("development")
    applicationDefaultJvmArgs = listOf("-Dio.ktor.development=$isDevelopment")
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("io.ktor:ktor-server-core:$ktor_version")
    implementation("io.ktor:ktor-server-netty:$ktor_version")
    implementation("io.ktor:ktor-server-content-negotiation:$ktor_version")
    implementation("io.ktor:ktor-serialization-kotlinx-json:$ktor_version")
    implementation("ch.qos.logback:logback-classic:$logback_version")
    implementation("com.cedarsoft.serialization:serialization:8.9.2")
    testImplementation("io.ktor:ktor-server-test-host:$ktor_version")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit:$kotlin_version")
    implementation("io.ktor:ktor-server-core-jvm:$ktor_version")
    implementation("io.ktor:ktor-server-auth-jvm:$ktor_version")
    implementation("io.ktor:ktor-server-locations-jvm:$ktor_version")
    implementation("io.ktor:ktor-server-netty-jvm:$ktor_version")
    implementation("ch.qos.logback:logback-classic:$logback_version")
    implementation("io.ktor:ktor-server-cors:$ktor_version")
    implementation("com.epam.drill.ktor:ktor-swagger:0.7.2-drill")
    implementation("io.insert-koin:koin-core:3.2.0")
    implementation("org.jetbrains.exposed:exposed-core:0.38.2")
    implementation("org.jetbrains.exposed:exposed-jdbc:$exposedVersion")
    implementation("org.jetbrains.exposed:exposed-dao:0.38.2")
    implementation("io.insert-koin:koin-ktor:3.2.0")
    implementation("mysql:mysql-connector-java:8.0.29")
    implementation("org.jetbrains.exposed:exposed-java-time:0.38.2")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.13.3")
    implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310:2.13.3")
    implementation("com.fasterxml.jackson.module:jackson-modules-base:2.13.3")
    implementation("com.fasterxml.jackson.module:jackson-module-scala_3:2.13.3")
    implementation("com.fasterxml.jackson.core:jackson-core:2.13.3")
    implementation("com.fasterxml.jackson.core:jackson-annotations:2.13.3")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.10.2")
    testImplementation("io.ktor:ktor-server-tests-jvm:$ktor_version")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit:$kotlin_version")
}