plugins {
    java
}

group = "ru.otus.2021.12.java.bulaev"
version = "0.0.1"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(Libs.junit)
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}