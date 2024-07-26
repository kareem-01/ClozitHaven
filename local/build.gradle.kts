plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    kotlin("kapt")
}

android {
    namespace = "com.example.local"
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
}

dependencies {
    implementation(project(Modules.REPOSITORY))

    implementation(Dependency.androidxCore)
    implementation(Dependency.appCompat)
    implementation(Dependency.googleMaterial)
    testImplementation(Dependency.junit)
    androidTestImplementation(Dependency.junitExtension)
    androidTestImplementation(Dependency.espresso)
    implementation(Dependency.dataStore)
    implementation(Dependency.hilt)
    kapt(Dependency.hiltCompiler)
    implementation(Dependency.roomKtx)
    implementation(Dependency.roomRuntime)
    kapt(Dependency.roomCompiler)
}