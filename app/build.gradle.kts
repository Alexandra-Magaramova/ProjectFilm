plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id ("kotlin-parcelize")
    id ("kotlin-kapt")
}
apply (from ="${project.rootDir}/versions_dagger.gradle")

android {
    namespace = "com.magaramova.projectfilm"
    compileSdk = 35

    buildFeatures {
        viewBinding = true
    }


    defaultConfig {
        applicationId = "com.magaramova.projectfilm"
        minSdk = 26
        targetSdk = 35
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        buildConfig = true
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.0"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.15.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.8.7")
    implementation("androidx.activity:activity-compose:1.9.3")
    implementation(platform("androidx.compose:compose-bom:2023.08.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    implementation ("androidx.constraintlayout:constraintlayout:2.2.0")
    implementation("androidx.appcompat:appcompat:1.7.0")
    implementation("androidx.core:core-ktx:1.15.0")
    implementation("androidx.cardview:cardview:1.0.0")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.2.1")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.6.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.08.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")
    //MaterialDesign
    implementation ("com.google.android.material:material:1.12.0")
    implementation ("androidx.recyclerview:recyclerview:1.3.2")
    implementation ("androidx.fragment:fragment:1.8.5")
    implementation ("com.jakewharton.timber:timber:5.0.1")
    implementation ("androidx.coordinatorlayout:coordinatorlayout:1.2.0")
    //Glide
    implementation ("com.github.bumptech.glide:glide:4.11.0")
    annotationProcessor ("com.github.bumptech.glide:compiler:4.11.0")
    //Retrofit
    implementation ("com.squareup.retrofit2:retrofit:2.9.0")
    implementation ("com.squareup.retrofit2:converter-gson:2.6.0")
    implementation ("com.squareup.okhttp3:logging-interceptor:3.12.6")
    //Koin
    implementation ("io.insert-koin:koin-android:3.5.3")

    //Dagger
    implementation("com.google.dagger:dagger:2.52")
    kapt ("com.google.dagger:dagger-compiler:2.52")

    //Remote module
    implementation (project(":remote_module"))

    implementation ("androidx.swiperefreshlayout:swiperefreshlayout:1.1.0")


    //implementation ("androidx.room:room-runtime:2.6.1")
    //kapt ("androidx.room:room-compiler:2.6.1")

    //Coroutines
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")

    implementation ("androidx.room:room-ktx:2.6.1")


    implementation ("androidx.room:room-runtime:2.6.1")
    implementation ("androidx.room:room-rxjava3:2.6.1")
    kapt ("androidx.room:room-compiler:2.6.1")

    //RxJava
    implementation ("io.reactivex.rxjava3:rxandroid:3.0.0")
    implementation ("io.reactivex.rxjava3:rxjava:3.0.10")

    implementation ("io.reactivex.rxjava3:rxkotlin:3.0.1")
    implementation ("com.github.akarnokd:rxjava3-retrofit-adapter:3.0.0")
}

