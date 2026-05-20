plugins {
    java
}

subprojects {
    apply(plugin = "java")

    repositories {
        mavenCentral()
    }

    dependencies {
        add("compileOnly", "org.projectlombok:lombok:1.18.38")
        add("annotationProcessor", "org.projectlombok:lombok:1.18.38")
        add("testCompileOnly", "org.projectlombok:lombok:1.18.38")
        add("testAnnotationProcessor", "org.projectlombok:lombok:1.18.38")
        add("testImplementation", "org.junit.jupiter:junit-jupiter:5.10.2")
        add(
            "testRuntimeOnly",
            "org.junit.platform:junit-platform-launcher:1.10.2"
        )
    }

    tasks.jar {
        archiveBaseName.set("s21_cli")
        archiveVersion.set("")
        destinationDirectory.set(file("$rootDir/out"))
        manifest {
            attributes(
                "Main-Class" to "org.school.Main"
            )
        }
        duplicatesStrategy = DuplicatesStrategy.EXCLUDE
        from({
            configurations.runtimeClasspath.get()
                .filter { it.name.endsWith(".jar") }
                .map { zipTree(it) }
        })
    }

    tasks.test {
        useJUnitPlatform()
        testLogging {
            events("passed", "failed", "skipped")
            showStandardStreams = true

            exceptionFormat =
                org.gradle.api.tasks.testing.logging.TestExceptionFormat.FULL
        }
    }
}