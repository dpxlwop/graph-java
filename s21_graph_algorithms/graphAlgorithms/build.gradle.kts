import org.gradle.kotlin.dsl.testImplementation

plugins {
    java
}

group = "org.school"
version = "1.0"

repositories {
    mavenCentral()
}

dependencies {
    implementation(files("libs/s21_containers.jar"))
    implementation(files("libs/s21_graph.jar"))
    testImplementation("org.junit.jupiter:junit-jupiter:5.10.2")
    testImplementation("org.junit.platform:junit-platform-suite:1.10.2")
}

tasks.jar {
    archiveBaseName.set("s21_graphAlgorithms")
    archiveVersion.set("")
    destinationDirectory.set(file("$rootDir/out"))
}

