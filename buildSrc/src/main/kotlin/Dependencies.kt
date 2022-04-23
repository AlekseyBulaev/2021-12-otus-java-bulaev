object Versions {
    const val guava = "31.0.1-jre"
    const val junit = "5.8.2"
    const val assertj = "3.22.0"
    const val postgres = "42.3.4"
    const val flyway = "8.5.9"
    const val logback = "1.3.0-alpha14"
    const val containerJUnit = "1.17.1"
    const val containerPostgres = "1.17.1"
    const val hikari = "5.0.1"
}

object Libs {
    const val guava = "com.google.guava:guava:${Versions.guava}"

    val testImplementation = listOf(
        "org.assertj:assertj-core:${Versions.assertj}",
        "org.junit.jupiter:junit-jupiter-api:${Versions.junit}",
        "org.testcontainers:junit-jupiter:${Versions.containerJUnit}",
        "org.testcontainers:postgresql:${Versions.containerPostgres}",
    )

    val testRuntimeOnly = listOf("org.junit.jupiter:junit-jupiter-engine")
    val postgres = "org.postgresql:postgresql:${Versions.postgres}"
    val flyway = "org.flywaydb:flyway-core:${Versions.flyway}"
    val logback = "ch.qos.logback:logback-classic:${Versions.logback}"
    val hikari = "com.zaxxer:HikariCP:${Versions.hikari}"
}