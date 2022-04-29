dependencies {
    implementation(Libs.postgres)
    implementation(Libs.flyway)
    implementation(Libs.logback)
    implementation(Libs.hikari)

    Libs.testImplementation.forEach(::testImplementation)
    Libs.testRuntimeOnly.forEach(::testRuntimeOnly)
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}