plugins {
    id("fabric-loom") version "1.7-SNAPSHOT"
    id("java")
    id("maven-publish")
}

group = providers.gradleProperty("maven_group")
version = providers.gradleProperty("mod_version")

java.toolchain.languageVersion.set(JavaLanguageVersion.of(21))

repositories {
    mainCentral()
    maven("https://maven.fabricmc.net/")
    maven("https://maven.fabricmc.net/repository/")
}

dependencies {
    minecraft("com.mojang:minecraft:\")
    mappings("net.fabricmc:yarn:\:v2")
    modImplementation("net.fabricmc:fabric-loader:\")
    modImplementation("net.fabricmc.fabric-api:fabric-api:\")
    modImplementation("me.shedaniel:cloth-config2:15.0.136")
}

loom {
    mappingsLayer.add("nested")
}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
    options.release.set(21)
}
