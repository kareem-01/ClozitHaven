// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    dependencies {

    }
}

plugins {
    id("com.android.application") version "8.5.0" apply false
    id("org.jetbrains.kotlin.android") version "2.0.0" apply false
    id("com.android.library") version "8.5.0" apply false
    id("org.jetbrains.kotlin.jvm") version "2.0.0" apply false
    id("com.google.dagger.hilt.android") version "2.51.1" apply false
    kotlin("kapt") version "2.0.0"
    alias(libs.plugins.compose.compiler) apply false
}
