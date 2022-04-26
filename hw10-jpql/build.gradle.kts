dependencies {
    implementation(Libs.postgres)
    implementation(Libs.flyway)
    implementation(Libs.logback)
    implementation(Libs.hikari)
    implementation(Libs.hibernate)
    implementation(Libs.javax)


    testImplementation(Libs.h2)
    Libs.testImplementation.forEach(::testImplementation)
    Libs.testRuntimeOnly.forEach(::testRuntimeOnly)
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}