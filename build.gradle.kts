plugins {
    `java-library`
    id("com.github.johnrengelman.shadow") version "7.1.2"
    `maven-publish`
}

group = "me.cocos"
version = "1.5-SNAPSHOT"

repositories {
    mavenCentral()
    maven {
        name = "spigotmc-repo"
        url = uri("https://hub.spigotmc.org/nexus/content/repositories/snapshots/")
    }
    maven {
        name = "sonatype"
        url = uri("https://oss.sonatype.org/content/groups/public/")
    }
}

dependencies {
    compileOnly ("org.spigotmc:spigot-api:1.8.8-R0.1-SNAPSHOT")

    val adventureVersion = "4.11.0"
    api("net.kyori:adventure-api:$adventureVersion")
    api("net.kyori:adventure-text-serializer-legacy:$adventureVersion")
    api("net.kyori:adventure-text-serializer-gson:$adventureVersion")
}


java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(17))
}
tasks {
    compileJava {
        options.encoding = "UTF-8"
    }
}


publishing {
    publications {
        create<MavenPublication>("maven") {
            groupId = "$group"
            artifactId = artifactId
            version = "${project.version}"

            from(components["java"])
        }
    }
    repositories {
        maven {
            name = "transtv-repository"
            url = uri("https://repo.transtv.pl/releases")

            credentials {
                username = System.getenv("TRANSTV_REPO_USERNAME")
                password = System.getenv("TRANSTV_REPO_PASSWORD")
            }
        }
    }
}