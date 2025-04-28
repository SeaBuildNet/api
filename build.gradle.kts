plugins {
    kotlin("jvm") version "2.1.10"
    `maven-publish`
}

group = "net.seabuild"
version = "1.0.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven("https://repo.papermc.io/repository/maven-public/")
    maven {
        url = uri("https://repo.flammenfuchs.de/public")
        name = "flammenfuchs-repo"
    }
    maven("https://repo.panda-lang.org/releases")
}

dependencies {
    testImplementation(kotlin("test"))
    compileOnly("io.papermc.paper:paper-api:1.21.5-R0.1-SNAPSHOT")
    implementation("de.flammenfuchs:injections:3.0.0")
    implementation("dev.rollczi:litecommands-bukkit:3.9.7")
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