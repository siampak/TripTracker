plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    //fore firebase connection
    id("com.google.gms.google-services")
    //for navigation var
    id("androidx.navigation.safeargs")
    //dagger hilt
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.example.tourmate"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.tourmate"
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
    buildFeatures {
        viewBinding = true
    }

    buildFeatures {
        dataBinding = true

    }
}

dependencies {



    // Import the Firebase BoM
    implementation(platform("com.google.firebase:firebase-bom:32.6.0"))
    implementation("com.google.firebase:firebase-analytics")

    implementation("com.google.firebase:firebase-auth")
    implementation("com.google.firebase:firebase-firestore")
    implementation("com.google.firebase:firebase-storage")

    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.10.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.6.2")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.2")
    implementation("androidx.navigation:navigation-fragment-ktx:2.7.5")
    implementation("androidx.navigation:navigation-ui-ktx:2.7.5")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    //Glide
    implementation("com.github.bumptech.glide:glide:4.16.0")
    kapt("com.github.bumptech.glide:compiler:4.16.0")

    //location
    implementation ("com.google.android.gms:play-services-location:21.0.1")

    //retrofit
    implementation ("com.squareup.retrofit2:retrofit:2.9.0")
    implementation ("com.squareup.retrofit2:converter-moshi:2.9.0")


    //Mushi
    implementation ("com.squareup.moshi:moshi-kotlin:1.12.0")


    //dagger- hilt
    implementation("com.google.dagger:hilt-android:2.48")
    kapt("com.google.dagger:hilt-android-compiler:2.48")

//    implementation ('com.github.bumptech.glide:glide:4.14.2')
//    annotationProcessor ('com.github.bumptech.glide:compiler:4.14.2')

}