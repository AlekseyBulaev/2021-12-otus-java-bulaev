object Versions {
    const val guava = "31.0.1-jre"
    const val junit = "5.8.2"
    const val assertj = "3.22.0"
    const val mockito = "4.5.0"
    const val jackson = "2.13.2.2"
}

object Libs {
    const val guava = "com.google.guava:guava:${Versions.guava}"
    const val jackson = "com.fasterxml.jackson.core:jackson-databind:${Versions.jackson}"

    val testImplementation = listOf(
        "org.assertj:assertj-core:${Versions.assertj}",
        "org.mockito:mockito-core:${Versions.mockito}",
        "org.junit.jupiter:junit-jupiter-api:${Versions.junit}",
        "org.junit.jupiter:junit-jupiter-engine:${Versions.junit}",
        "org.junit.jupiter:junit-jupiter-params:${Versions.junit}",
        "org.mockito:mockito-junit-jupiter:${Versions.mockito}",
    )

    val testRuntimeOnly = listOf("org.junit.jupiter:junit-jupiter-engine")
}