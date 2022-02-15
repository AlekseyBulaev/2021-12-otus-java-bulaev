object Versions {
    const val guava = "31.0.1-jre"
    const val junit = "5.8.2"
    const val assertj = "3.22.0"
}

object Libs {
    const val guava = "com.google.guava:guava:${Versions.guava}"

    val testImplementation = listOf(
        "org.junit.jupiter:junit-jupiter-api:${Versions.assertj}",
        "org.junit.jupiter:junit-jupiter-api:${Versions.junit}",
    )

    val testRuntimeOnly = listOf("org.junit.jupiter:junit-jupiter-engine")
}