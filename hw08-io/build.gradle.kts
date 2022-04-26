dependencies {
    Libs.testImplementation.forEach(::testImplementation)
    Libs.testRuntimeOnly.forEach(::testRuntimeOnly)

    implementation(Libs.guava)
    implementation(Libs.jackson)
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}