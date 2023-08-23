plugins {
    id("java-library")
    id("org.jetbrains.kotlin.jvm")
}

java {
    sourceCompatibility = Versions.javaVersion
    targetCompatibility = Versions.javaVersion
}
dependencies {
    api(project(Modules.ENTITY))
    implementation(Dependency.dagger)
}