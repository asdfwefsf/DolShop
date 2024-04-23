import org.jetbrains.kotlin.kapt3.base.Kapt.kapt
import org.jetbrains.kotlin.konan.properties.Properties

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id ("com.google.dagger.hilt.android")
    id ("kotlin-kapt")
    id ("com.google.gms.google-services")

}
//
//val properties = Properties()
//properties.load(project.rootProject.file("local.properties").inputStream())

android {
    namespace = "com.company.presentation"
    compileSdk = 34

    defaultConfig {
        minSdk = 30

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")

//        buildConfigField("String" , "KAKAO_NATIVE_APP_KEY" ,
//            "\"${properties["KAKAO_NATIVE_APP_KEY"]}\""
//        )
//        resValue("string" , "kakao_oauth_host" , "\"${properties["kakao_oauth_host"]}\"")
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
        jvmTarget = JavaVersion.VERSION_17.toString()
    }
    //
    buildFeatures {
        compose = true
//        buildConfig = true
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

    implementation(project(":domain"))
    implementation("com.google.dagger:hilt-android:2.49")
    kapt("com.google.dagger:hilt-android-compiler:2.49")
    kapt ("androidx.hilt:hilt-compiler:1.2.0")

    //
    navigation()
    kakao()
//    firebase()
    api(platform("com.google.firebase:firebase-bom:32.8.1"))
    api("com.google.firebase:firebase-database-ktx")

    //
    coil()
    constraint()
    splash()



    implementation ("androidx.compose.runtime:runtime:1.6.5")
    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.7.0")
    implementation("androidx.activity:activity-compose:1.8.2")
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
}
kapt {
    correctErrorTypes = true
}
