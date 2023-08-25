plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    kotlin("kapt")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.example.ui"
    compileSdk = 33

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = Versions.javaVersion
        targetCompatibility = Versions.javaVersion
    }
    kotlinOptions {
        jvmTarget = Versions.jvmTarget
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    kapt {
        correctErrorTypes = true
    }
}

dependencies {
    implementation(project(Modules.VIEWMODEL))

    implementation(Dependency.constraintLayout)
    Dependency.composeGroup.forEach {
        implementation(it)
    }

    implementation(Dependency.hilt)
    kapt(Dependency.hiltCompiler)

    implementation(Dependency.androidxCore)
    implementation(Dependency.appCompat)
    implementation(Dependency.googleMaterial)
    testImplementation(Dependency.junit)
    androidTestImplementation(Dependency.junitExtension)

    implementation(Dependency.hiltNavigation)

}