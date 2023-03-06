group = "ru.otus.2021.12.java.bulaev"
version = "0.0.1"

plugins {
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
    id("com.github.johnrengelman.shadow") version "7.1.2" apply false
    id("org.springframework.boot") version "2.7.9" apply false
    id("com.google.cloud.tools.jib") version "3.2.1" apply false
    id("fr.brouillard.oss.gradle.jgitver") version "0.10.0-rc03" apply false
}

allprojects {
    group = "ru.otus"
    version = rootProject.version

    apply {
        plugin("java")
        plugin("io.spring.dependency-management")
    }
    repositories {
        mavenLocal()
        mavenCentral()
    }

    dependencyManagement {
        dependencies {
            imports {
                mavenBom("org.springframework.boot:spring-boot-dependencies:3.0.1")
                mavenBom("org.testcontainers:testcontainers-bom:1.17.3")
                mavenBom("com.google.protobuf:protobuf-bom:3.21.1")
            }
            dependency("com.google.guava:guava:${Versions.guava}")
            dependency("org.openjdk.jmh:jmh-core:${Versions.jmh}")
            dependency("org.openjdk.jmh:jmh-generator-annprocess:${Versions.jmh}")
            dependency("org.ow2.asm:asm-commons:${Versions.asm}")
            dependency("org.glassfish:jakarta.json:${Versions.glassfishJson}")

            dependency("org.projectlombok:lombok:${Versions.lombok}")
            dependency("com.google.code.gson:gson:${Versions.gson}")
            dependency("com.datastax.oss:java-driver-core:${Versions.cassandra}")

            dependency("org.mongodb:mongodb-driver-core:${Versions.mongodb}")
            dependency("org.mongodb:mongodb-driver-sync:${Versions.mongodb}")
            dependency("org.mongodb:bson:${Versions.mongodb}")
            dependency("org.mongodb:mongodb-driver-reactivestreams:${Versions.mongodbReactive}")
            dependency("org.neo4j.driver:neo4j-java-driver:${Versions.neo4j}")
            dependency("redis.clients:jedis:${Versions.jedis}")

            dependency("org.webjars:sockjs-client:${Versions.sockjs}")
            dependency("org.webjars:stomp-websocket:${Versions.stomp}")
            dependency("org.webjars:bootstrap:${Versions.bootstrap}")
            dependency("org.springdoc:springdoc-openapi-starter-webmvc-ui:${Versions.springDocOpenapiUi}")
            dependency("com.google.code.findbugs:jsr305:${Versions.jsr305}")

            dependency("io.grpc:grpc-netty:${Versions.grpc}")
            dependency("io.grpc:grpc-protobuf:${Versions.grpc}")
            dependency("io.grpc:grpc-stub:${Versions.grpc}")
            dependency("com.github.tomakehurst:wiremock:${Versions.wiremock}")
            dependency("io.r2dbc:r2dbc-postgresql:${Versions.r2dbcPostgresql}")
        }
    }

    configurations.configureEach {
        resolutionStrategy {
            failOnVersionConflict()

            force("javax.servlet:servlet-api:2.4")
            force("commons-logging:commons-logging:1.1.1")
            force("commons-lang:commons-lang:2.5")
            force("org.codehaus.jackson:jackson-core-asl:1.8.8")
            force("org.codehaus.jackson:jackson-mapper-asl:1.8.3")
            force("org.codehaus.jettison:jettison:1.1")
            force("net.java.dev.jna:jna:5.8.0")
            force("com.google.errorprone:error_prone_annotations:2.7.1")
            force("org.ow2.asm:asm:9.4")
        }
    }
}