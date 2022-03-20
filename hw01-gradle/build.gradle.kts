dependencies {
    implementation(Libs.guava)

    Libs.testImplementation.forEach(::testImplementation)
    Libs.testRuntimeOnly.forEach(::testRuntimeOnly)
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}

tasks.withType<Jar> {
    configurations["compileClasspath"].forEach{ file: File ->
        from(zipTree(file.absolutePath))
    }
//https://www.youtube.com/watch?v=G-v4AW_RnvM
    configurations["testCompileClasspath"].forEach { file: File ->
        from(zipTree(file.absolutePath))
    }
}