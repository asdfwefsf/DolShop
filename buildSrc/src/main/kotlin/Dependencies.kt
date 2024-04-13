import org.gradle.api.artifacts.dsl.DependencyHandler

object Dependencies {
    // implementation
    const val CORE = "androidx.core:core-ktx:${Versions.CORE}"
    const val LIFECYCLEKTX = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.LIFECYCLEKTX}"
    const val ACTIVITYCOMPOSE = "androidx.activity:activity-compose:${Versions.ACTIVITYCOMPOSE}"
    const val COMPOSEBOM = "androidx.compose:compose-bom:${Versions.COMPOSEBOM}"
    const val UI = "androidx.compose.ui:ui"
    const val UIGRAPHICS = "androidx.compose.ui:ui-graphics"
    const val UITOOLINGPREVIEW = "androidx.compose.ui:ui-tooling-preview"
    const val MATERIAL3 = "androidx.compose.material3:material3"
    const val APPCOMPAT = "androidx.appcompat:appcompat:${Versions.APPCOMPAT}"
    const val MATERIAL = "com.google.android.material:material:${Versions.MATERIAL}"

    // testImplementation
    const val JUNIT = "junit:junit:${Versions.JUNIT}"

    // androidTestImplementation
    const val EXTJUNIT = "androidx.test.ext:junit:${Versions.EXTJUNIT}"
    const val ESPRESSOCORE = "androidx.test.espresso:espresso-core:${Versions.ESPRESSOCORE}"
    const val PLATFORMCOMPOSEBOM = "androidx.compose:compose-bom:${Versions.COMPOSEBOM}"
    const val UITESTJUNIT = "androidx.compose.ui:ui-test-junit4"

    // debugImplementation
    const val UITOOLING = "androidx.compose.ui:ui-tooling"
    const val UITESTMANIFEST = "androidx.compose.ui:ui-test-manifest"

    // Retrofit
    const val RETROFIT = "com.squareup.retrofit2:retrofit:${Versions.RETROFIT}"
    // Gson
    const val GSON = "com.squareup.retrofit2:converter-gson:${Versions.GSON}"
    // OkHttp
    const val OKHTTP = "com.squareup.okhttp3:okhttp:${Versions.OKHTTP}"
    // Hilt
    const val HILTANDROID = "com.google.dagger:hilt-android:${Versions.HILTANDROID}"
    // HiltCompiler
    const val HILTANDROIDCOMPILER = "com.google.dagger:hilt-android-compiler:${Versions.HILTANDROIDCOMPILER}"
    // Compose_Navigation
    const val COMPOSENAVIGATION = "androidx.navigation:navigation-compose:${Versions.COMPOSENAVIGATION}"
    // Hilt_Navegation
    const val HILTNAVIGATION = "androidx.hilt:hilt-navigation-compose:${Versions.HILTNAVIGATION}"
    // ViewModel
    const val VIEWMODEL = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.LIFECYCLE}"
    // Compose_VIEWMODEL
    const val COMPOSE_VIEWMODEL = "androidx.lifecycle:lifecycle-viewmodel-compose:${Versions.LIFECYCLE}"
    // RoomDB
    const val roomKtx  = "androidx.room:room-ktx:${Versions.room}"
    // RoomDB_Compiler
    const val roomCompiler  = "androidx.room:room-compiler:${Versions.room}"
    // Room Runtime
    const val roomRuntime = "androidx.room:room-runtime:${Versions.room}"

    // CoilCompose
    const val COILCOMPOSE = "io.coil-kt:coil-compose:${Versions.COILCOMPOSE}"
    // ComposeLifeCycle
    const val COMPOSELIFECYCLE = "androidx.lifecycle:lifecycle-runtime-compose:${Versions.COMPOSELIFECYCLE}"
    // ComposeConstraint
    const val COMPOSECONSTRAINT = "androidx.constraintlayout:constraintlayout-compose:${Versions.COMPOSECONSTRAINT}"
    // SplashScreen
    const val SPLASHSCREEN = "androidx.core:core-splashscreen:${Versions.SPLASHSCREEN}"
}

fun DependencyHandler.room() {
    implementation(Dependencies.roomRuntime)
    implementation(Dependencies.roomKtx )
    kapt(Dependencies.roomCompiler)
}

//fun DependencyHandler.retrofit() {
//    implementation(Dependencies.retrofit)
//    implementation(Dependencies.moshiConverter)
//    implementation(Dependencies.okHttp)
//    implementation(Dependencies.okHttpLoggingInterceptor)
//}
//
//fun DependencyHandler.compose() {
//    implementation(Dependencies.composeUi)
//    implementation(Dependencies.composeRuntime)
//    implementation(Dependencies.composeUiGraphics)
//    implementation(Dependencies.composeUiTooling)
//    implementation(Dependencies.composeMaterial)
//    debugImplementation(Dependencies.composeUiToolingPreview)
//}
//
//fun DependencyHandler.hilt() {
//    implementation(Dependencies.hiltAndroid)
//    kapt(Dependencies.hiltCompiler)
//}