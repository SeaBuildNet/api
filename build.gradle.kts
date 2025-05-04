plugins {
    kotlin("jvm") version "2.1.10"
    `maven-publish`
}

group = "net.seabuild"
version = "1.0.0-SNAPSHOT"

repositories {
    mavenCentral()
    mavenLocal()
    maven("https://repo.papermc.io/repository/maven-public/")
    maven {
        url = uri("https://repo.flammenfuchs.de/public")
        name = "flammenfuchs-repo"
    }
    maven("https://repo.panda-lang.org/releases")
    maven("https://repo.repsy.io/mvn/reapermaga/library")
}

dependencies {
    testImplementation(kotlin("test"))
    compileOnly("io.papermc.paper:paper-api:1.21.5-R0.1-SNAPSHOT")
    api("de.flammenfuchs:injections:3.1.0")
    api("dev.rollczi:litecommands-bukkit:3.9.7")
    api("org.mongodb:mongodb-driver-kotlin-coroutine:5.4.0") {
        exclude("org.jetbrains.kotlinx", "kotlinx-coroutines-core")
    }
    api("net.kyori:adventure-platform-bukkit:4.3.4")
    api("com.github.reapermaga.library:common:0.1.13")
    api("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.10.1")
    api("dev.rollczi:litecommands-jakarta:3.9.6")
    api("dev.rollczi:litecommands-adventure-platform:3.9.6")
}

publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            from(components["java"])

            groupId = group.toString()
            artifactId = project.findProperty("artifactId") as String? ?: project.name
            version = version.toString()
        }
    }
    repositories {
        maven {
            url = uri("https://repo.flammenfuchs.de/public")
            name = "flammenfuchs-repo"
            credentials {
                username = System.getenv("MAVEN_REPO_USER")
                password = System.getenv("MAVEN_REPO_PASSWORD")
            }
        }
    }
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(21)
}