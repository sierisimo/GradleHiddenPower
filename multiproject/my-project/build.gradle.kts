plugins {
//    kotlin("jvm")
    application
}

application {
    mainClassName = project.findProperty("chapter")?.toString() ?: "com.sierisimo.chapter.guadalajara.Main"
}

dependencies {
    implementation(project(":my-lib"))
    implementation(project(":my-other-lib"))
}