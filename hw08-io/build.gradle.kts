dependencies {
    Libs.testImplementation.forEach(::testImplementation)
    Libs.testRuntimeOnly.forEach(::testRuntimeOnly)

    implementation(Libs.guava)
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}