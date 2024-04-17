plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    alias(libs.plugins.ktlint)
    id("com.google.devtools.ksp")
    id("com.google.dagger.hilt.android")
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.example.snsproject"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.snsproject"
        minSdk = 26
        targetSdk = 34
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
                getDefaultProguardFile(
                    "proguard-android-optimize.txt",
                ),
                "proguard-rules.pro",
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.3"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx, )
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.compose.bom, ), )
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics )
    implementation(libs.androidx.compose.ui.ui.tooling.preview )
    implementation(libs.androidx.compose.material3, )
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core, )
    androidTestImplementation(platform(libs.androidx.compose.compose.bom, ), )
    androidTestImplementation(libs.androidx.compose.ui.ui.test.junit4, )
    debugImplementation(libs.androidx.compose.ui.ui.tooling, )
    debugImplementation(libs.androidx.compose.ui.ui.test.manifest, )

    //retrofit
    implementation(libs.retrofit)
    implementation(libs.converter.gson)

    //hilt
    implementation(libs.hilt.android)
    ksp(libs.hilt.android.compiler)

    //orbit
    implementation(libs.orbit.viewmodel)

    //coil
    implementation(libs.coil.compose)

    //paging
    implementation(libs.androidx.paging.compose)
    implementation(libs.androidx.paging.runtime)

    // alternatively - without Android dependencies for tests
    testImplementation(libs.androidx.paging.common)

    //coroutine-core
    implementation(libs.kotlinx.coroutines.core)

    //room
    implementation(libs.androidx.room.runtime)
    annotationProcessor(libs.androidx.room.compiler)
    ksp(libs.androidx.room.compiler)

    // ktlintRuleset("io.nlopez.compose.rules:ktlint:0.3.13")
    // ktlintRuleset("com.twitter.compose.rules:ktlint:0.0.26")
    implementation(project(":data"))
    implementation(project(":domain"))
    implementation(project(":presentation"))
}
