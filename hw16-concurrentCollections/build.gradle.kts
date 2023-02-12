dependencies {
    implementation(Libs.logback)
    implementation(Libs.lombok)

    Libs.testImplementation.forEach(::testImplementation)
    Libs.testRuntimeOnly.forEach(::testRuntimeOnly)
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
    testLogging {
        events("passed", "skipped", "failed")
    }
}