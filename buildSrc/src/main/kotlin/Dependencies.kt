object Versions {
    const val guava = "31.0.1-jre"
    const val junit = "5.8.2"
    const val assertj = "3.22.0"
    const val mockito = "4.5.0"
    const val jackson = "2.13.2.2"
    const val postgres = "42.3.4"
    const val flyway = "8.5.9"
    const val logback = "1.3.0-alpha14"
    const val containerJUnit = "1.17.1"
    const val containerPostgres = "1.17.1"
    const val hikari = "5.0.1"
    const val hibernate = "5.6.5.Final"
    const val h2 = "2.1.212"
    const val javax = "2.2"
    const val lombok = "1.18.22"

}

object Libs {
    const val guava = "com.google.guava:guava:${Versions.guava}"
    const val jackson = "com.fasterxml.jackson.core:jackson-databind:${Versions.jackson}"
    const val postgres = "org.postgresql:postgresql:${Versions.postgres}"
    const val flyway = "org.flywaydb:flyway-core:${Versions.flyway}"
    const val logback = "ch.qos.logback:logback-classic:${Versions.logback}"
    const val hikari = "com.zaxxer:HikariCP:${Versions.hikari}"
    const val hibernate = "org.hibernate:hibernate-core:${Versions.hibernate}"
    const val h2 = "com.h2database:h2:${Versions.h2}"
    const val javax = "javax.persistence:javax.persistence-api:${Versions.javax}"
    const val lombok = "org.projectlombok:lombok:${Versions.lombok}"

    val testImplementation = listOf(
        "org.assertj:assertj-core:${Versions.assertj}",
        "org.mockito:mockito-core:${Versions.mockito}",
        "org.mockito:mockito-junit-jupiter:${Versions.mockito}",

        "org.junit.jupiter:junit-jupiter-api:${Versions.junit}",
        "org.testcontainers:junit-jupiter:${Versions.containerJUnit}",
        "org.testcontainers:postgresql:${Versions.containerPostgres}",
        "org.junit.jupiter:junit-jupiter-engine:${Versions.junit}",
        "org.junit.jupiter:junit-jupiter-params:${Versions.junit}",

        "org.testcontainers:junit-jupiter:${Versions.containerJUnit}",
        "org.testcontainers:postgresql:${Versions.containerPostgres}",
    )
    val testRuntimeOnly = listOf("org.junit.jupiter:junit-jupiter-engine")
}