dependencies {
    implementation(Libs.postgres)
    implementation(Libs.flyway)
    implementation(Libs.logback)
    implementation(Libs.hikari)

    implementation(project(":hw11-cache"))
    Libs.testImplementation.forEach(::testImplementation)
    Libs.testRuntimeOnly.forEach(::testRuntimeOnly)
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}