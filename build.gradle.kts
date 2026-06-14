plugins {
    id("fabric-loom") version "1.7-SNAPSHOT"
    id("java")
    id("maven-publish")
}

group = "com.example"
version = "1.0.0"
java.toolchain.languageVersion.set(JavaLanguageVersion.of(21))

repositories {
    mavenCentral()
    maven("https://maven.fabricmc.net/")
    maven("https://maven.shedaniel.me/")
    maven("https://maven.terraformersmc.com/")
}

dependencies {
    minecraft("com.mojang:minecraft:1.21.1")
    mappings("net.fabricmc:yarn:1.21.1+build.3:v2")
    modImplementation("net.fabricmc:fabric-loader:0.16.9")
    modImplementation("net.fabricmc.fabric-api:fabric-api:0.102.0+1.21.1")
    modImplementation("me.shedaniel.cloth:cloth-config-fabric:13.0.121") {
        exclude(group = "net.fabricmc.fabric-api")
    }
}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
    options.release.set(21)
}
