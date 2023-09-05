plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    kotlin("kapt")
    id("com.google.dagger.hilt.android")
}



android {
    namespace = "com.example.clozithaven"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.example.clozithaven"
        minSdk = 24
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }

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
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    kapt {
        correctErrorTypes = true
    }
}

dependencies {

    implementation(project(Modules.UI))
    implementation(project(Modules.USECASE))
    implementation(project(Modules.ENTITY))
    implementation(project(Modules.LOCAL))
    implementation(project(Modules.REMOTE))
    implementation(project(Modules.REPOSITORY))
    implementation(project(Modules.VIEWMODEL))


    implementation(Dependency.hilt)
    kapt(Dependency.hiltCompiler)
    implementation(Dependency.retrofit)
    implementation(Dependency.gsonConverter)
    implementation(Dependency.logging)
implementation(Dependency.dataStore)

}