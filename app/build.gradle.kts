plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
}

android {
    namespace = "com.example.roomdemo"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.roomdemo"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"

    }
    buildFeatures{
        viewBinding = true
    }
}

dependencies {


    val roomVersion = "2.3.0"
    val activityVersion = "1.3.1"
    // Room and Lifecycle dependencies
    //noinspection GradleDependency
    implementation ("androidx.room:room-runtime:$roomVersion")
    //noinspection KaptUsageInsteadOfKsp,GradleDependency
    kapt ("androidx.room:room-compiler:$roomVersion")
    //kotlin extensions for coroutine support with room
    //noinspection GradleDependency
    implementation("androidx.room:room-ktx:$roomVersion")
    //kotlin extension for coroutine support with activities
    //noinspection GradleDependency
    implementation ("androidx.activity:activity-ktx:$activityVersion")
    //noinspection GradleDependency
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.3.3")
    //noinspection GradleDependency
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.3.3")


    implementation ("androidx.room:room-runtime:2.6.1")
    implementation ("androidx.room:room-ktx:2.6.1")
    //noinspection KaptUsageInsteadOfKsp
    kapt ("androidx.room:room-compiler:2.6.1")
    implementation ("androidx.room:room-testing:2.6.1")

    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}