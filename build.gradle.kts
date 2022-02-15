group = "ru.otus.2021.12.java.bulaev"
version = "0.0.1"

subprojects {
    group = rootProject.group
    version = rootProject.version

    apply {
        plugin("java")
    }

    repositories {
        mavenCentral()
    }
}