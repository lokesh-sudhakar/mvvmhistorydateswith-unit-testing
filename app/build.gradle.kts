plugins {
  id("com.android.application")
  id("org.jetbrains.kotlin.android")
  kotlin("kapt")
  id("com.google.dagger.hilt.android")
}

android {
  namespace = "com.technocraze.mvvmdatehistroy"
  compileSdk = 34

  defaultConfig {
    applicationId = "com.technocraze.mvvmdatehistroy"
    minSdk = 24
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
      proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
    }
  }
  // compileOptions {
  //   sourceCompatibility = JavaVersion.VERSION_1_8
  //   targetCompatibility = JavaVersion.VERSION_1_8
  // }
  // kotlinOptions {
  //   jvmTarget = "1.8"
  // }
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

  implementation("androidx.core:core-ktx:1.9.0")
  implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")
  implementation("androidx.activity:activity-compose:1.8.0")
  implementation(platform("androidx.compose:compose-bom:2023.03.00"))
  implementation("androidx.compose.ui:ui")
  implementation("androidx.compose.ui:ui-graphics")
  implementation("androidx.compose.ui:ui-tooling-preview")
  implementation("androidx.compose.material3:material3")
  testImplementation("junit:junit:4.13.2")
  androidTestImplementation("androidx.test.ext:junit:1.1.5")
  androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
  androidTestImplementation(platform("androidx.compose:compose-bom:2023.03.00"))
  androidTestImplementation("androidx.compose.ui:ui-test-junit4")
  debugImplementation("androidx.compose.ui:ui-tooling")
  debugImplementation("androidx.compose.ui:ui-test-manifest")

  implementation("com.google.dagger:hilt-android:2.44")
  kapt("com.google.dagger:hilt-android-compiler:2.44")

  implementation("com.squareup.retrofit2:retrofit:2.9.0")
  implementation("com.squareup.okhttp3:okhttp:4.7.2")
  implementation("com.squareup.okhttp3:logging-interceptor:4.7.2")
  implementation("com.squareup.retrofit2:converter-gson:2.9.0")
  implementation("com.google.code.gson:gson:2.8.6")
  implementation("androidx.constraintlayout:constraintlayout-compose:1.1.0-alpha13")

  testImplementation("com.squareup.okhttp3:mockwebserver:4.9.0")

  testImplementation("org.mockito:mockito-core:3.6.0")
  testImplementation("com.nhaarman.mockitokotlin2:mockito-kotlin:2.2.0")
  testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.6.4")
  testImplementation("androidx.arch.core:core-testing:2.1.0")
  androidTestImplementation("androidx.arch.core:core-testing:2.1.0")

  val mockk_version = "1.13.7"
  testImplementation("io.mockk:mockk:$mockk_version")
  androidTestImplementation("io.mockk:mockk-android:$mockk_version")


  testImplementation ("app.cash.turbine:turbine:1.0.0")


  testImplementation("io.mockk:mockk:1.12.0")
  androidTestImplementation("io.mockk:mockk-android:1.12.0")
  androidTestImplementation("androidx.arch.core:core-testing:2.2.0")
}
kapt {
  correctErrorTypes = true
}