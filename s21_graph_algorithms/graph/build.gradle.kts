import org.gradle.kotlin.dsl.java

plugins {
    java
}

group = "org.school"
version = "1.0"

tasks.jar {
    archiveBaseName.set("s21_graph")
    archiveVersion.set("")
    destinationDirectory.set(file("$rootDir/out"))
}

