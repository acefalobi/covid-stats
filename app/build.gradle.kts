import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("android.extensions")
    kotlin("plugin.allopen")
    kotlin("kapt")
    id(Plugin.navigation)
}

android {
    compileSdkVersion(Versions.compileSdkVersion)

    defaultConfig {
        applicationId = BuildConfiguration.applicationId
        minSdkVersion(Versions.minSdkVersion)
        targetSdkVersion(Versions.targetSdkVersion)

        multiDexEnabled = true

        buildConfigField("String", "BASE_URL", BuildConfiguration.BASE_URL)

        versionCode = Versions.versionCode
        versionName = Versions.versionName

        vectorDrawables.useSupportLibrary = true

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    viewBinding {
        isEnabled = true
    }

    androidExtensions {
        isExperimental = true
    }

    tasks.withType<KotlinCompile> {
        kotlinOptions.jvmTarget = "1.8"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    testOptions {
        unitTests.isIncludeAndroidResources = true
    }
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation(RootDependencies.kotlin)

    implementation(AndroidXDependencies.appCompat)
    implementation(AndroidXDependencies.constraintLayout)
    implementation(AndroidXDependencies.coreKtx)
    implementation(AndroidXDependencies.lifecycle)
    implementation(AndroidXDependencies.multiDex)
    implementation(AndroidXDependencies.navigationFragmentKtx)
    implementation(AndroidXDependencies.navigationUiKtx)
    kapt(AndroidXDependencies.lifecycleCompiler)

    implementation(ViewDependencies.androidMaps)
    implementation(ViewDependencies.coil)
    implementation(ViewDependencies.materialComponent)
    implementation(ViewDependencies.shimmerLayout)

    implementation(AsyncDependencies.coroutines)
    implementation(AsyncDependencies.coroutinesAndroid)

    implementation(PlayServicesDependencies.googleLocation)
    implementation(PlayServicesDependencies.googleMaps)

    implementation(DependencyInjectionDependencies.dagger)
    implementation(DependencyInjectionDependencies.daggerAndroidSupport)
    kapt(DependencyInjectionDependencies.daggerCompiler)
    kapt(DependencyInjectionDependencies.daggerAndroidProcessor)

    implementation(UtilityDependencies.gson)
    implementation(UtilityDependencies.timber)

    implementation(PersistenceDependencies.room)
    implementation(PersistenceDependencies.roomKtx)
    kapt(PersistenceDependencies.roomCompiler)

    implementation(NetworkDependencies.retrofit)
    implementation(NetworkDependencies.okhttp)
    implementation(NetworkDependencies.gsonConverter)
    implementation(NetworkDependencies.loggingInterceptor)
    implementation(NetworkDependencies.rxJavaAdapter)

    testImplementation(TestingDependencies.jUnit)
    testImplementation(TestingDependencies.mockitoCore)
    testImplementation(TestingDependencies.roboelectric)
    androidTestImplementation(TestingDependencies.androidJUnit)
    testImplementation(TestingDependencies.androidTest)
    androidTestImplementation(TestingDependencies.androidTestRunner)

}
