plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    kotlin("kapt")
    id("com.google.dagger.hilt.android")
    alias(libs.plugins.compose.compiler)

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

    implementation("com.github.skydoves:orchestra-colorpicker:1.2.0")
    implementation("com.github.skydoves:colorpicker-compose:1.0.4")
    implementation(Dependency.androidxCore)
    implementation(Dependency.appCompat)
    implementation(Dependency.googleMaterial)
    testImplementation(Dependency.junit)
    androidTestImplementation(Dependency.junitExtension)
    implementation("com.eygraber:compose-color-picker:0.0.14")
    implementation("com.google.accompanist:accompanist-systemuicontroller:0.27.0")
    implementation(Dependency.coil)
    implementation(Dependency.hiltNavigation)
    implementation(Dependency.splashScreen)

}