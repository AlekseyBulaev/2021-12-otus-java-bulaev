object Versions {
    const val junit = "5.9.1"
    const val assertj = "3.22.0"
    const val mockito = "4.5.0"
    const val jackson = "2.14.1"
    const val postgres = "42.3.4"
    const val flyway = "8.5.9"
    const val logback = "1.3.0-alpha14"
    const val containerJUnit = "1.17.1"
    const val containerPostgres = "1.17.1"
    const val hikari = "5.0.1"
    const val hibernate = "5.6.5.Final"
    const val h2 = "2.1.212"
    const val javax = "2.2"
    const val faker = "1.0.2"
    const val guava = "31.1-jre"
    const val jmh = "1.35"
    const val asm = "9.2"
    const val glassfishJson = "2.0.1"

    const val lombok = "1.18.24"
    const val gson = "2.10.1"

    const val mongodb = "4.7.1"
    const val mongodbReactive = "4.7.1"
    const val cassandra = "4.15.0"
    const val neo4j = "4.4.9"
    const val jedis = "4.2.3"

    const val jetty = "11.0.7"
    const val freemarker = "2.3.31"

    const val reflections = "0.10.2"

    const val sockjs = "1.5.1"
    const val stomp = "2.3.4"
    const val bootstrap = "5.2.3"
    const val springDocOpenapiUi = "2.0.2"
    const val jsr305 = "3.0.2"

    const val errorProneAnnotations = "2.11.0"
    const val tomcatAnnotationsApi = "6.0.53"
    const val grpc = "1.44.1"
    const val wiremock = "3.0.0-beta-2"
    const val r2dbcPostgresql = "0.8.13.RELEASE"
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
    const val gson = "com.google.code.gson:gson:${Versions.gson}"
    const val freemarker = "org.freemarker:freemarker:${Versions.freemarker}"
    const val faker = "com.github.javafaker:javafaker:${Versions.faker}"
    const val junit = "org.junit.jupiter:junit-jupiter-api:${Versions.junit}"

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

    val jetty = listOf(
    "org.eclipse.jetty:jetty-servlet:${Versions.jetty}",
    "org.eclipse.jetty:jetty-server:${Versions.jetty}",
    "org.eclipse.jetty:jetty-webapp:${Versions.jetty}",
    "org.eclipse.jetty:jetty-security:${Versions.jetty}",
    "org.eclipse.jetty:jetty-http:${Versions.jetty}",
    "org.eclipse.jetty:jetty-io:${Versions.jetty}",
    "org.eclipse.jetty:jetty-util:${Versions.jetty}",
    )
    val testRuntimeOnly = listOf("org.junit.jupiter:junit-jupiter-engine")
}