plugins {
    id("java")
}

group = "org.school"
version = "unspecified"

repositories {
    mavenCentral()
}

dependencies {
    implementation(files("libs/s21_graph.jar"))
    implementation(files("libs/s21_graphAlgorithms.jar"))
    testImplementation(platform("org.junit:junit-bom:6.0.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.jar {
    archiveBaseName.set("s21_cli")
    archiveVersion.set("")
    destinationDirectory.set(file("$rootDir/out"))
}

tasks.test {
    useJUnitPlatform()
}