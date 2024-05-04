import org.jetbrains.kotlin.kapt3.base.Kapt.kapt

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id ("com.google.dagger.hilt.android")
    id ("kotlin-kapt")
}

android {
    namespace = "com.company.data"
    compileSdk = 33

    defaultConfig {
        minSdk = 30

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")

        //
        buildConfigField("String" , "KAKAO_NATIVE_APP_KEY" ,
            "\"${properties["KAKAO_NATIVE_APP_KEY"]}\""
        )
        resValue("string" , "kakao_oauth_host" , "\"${properties["kakao_oauth_host"]}\"")
        //
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
//        compose = true
        buildConfig = true
    }
//    composeOptions {
//        kotlinCompilerExtensionVersion = "1.4.3"
//    }
//    packaging {
//        resources {
//            excludes += "/META-INF/{AL2.0,LGPL2.1}"
//        }
//    }
    //
}

dependencies {
    implementation(project(":domain"))
    implementation(project(":core:network:product"))
    implementation(project(":core:network:announcement"))
    implementation(project(":datasource-local-productsale"))
    implementation(project(":datasource-local-delivery"))
    implementation(project(":data:data-datasource"))
    implementation(project(":data:data-impl:update-productsale-impl"))
    implementation(project(":data:data-mapper:saletodomain"))

    // 차후에 수정
    implementation ("androidx.paging:paging-runtime-ktx:3.1.1")
    implementation ("androidx.paging:paging-compose:1.0.0-alpha18")
    implementation ("androidx.room:room-paging:2.4.3")
    api(platform("com.google.firebase:firebase-bom:32.8.1"))
    api("com.google.firebase:firebase-database-ktx")
    api("com.google.firebase:firebase-storage-ktx:20.3.0")
//    implementation(project(":presentation"))
    // 차후에 수정



    implementation("com.google.dagger:hilt-android:2.49")
    kapt ("androidx.hilt:hilt-compiler:1.2.0")
    kapt("com.google.dagger:hilt-android-compiler:2.49")

    kakao()
    room()
    worker()
    hiltWorker()
//    implementation ("androidx.compose.runtime:runtime:1.6.5")

    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}

kapt {
    correctErrorTypes = true
}