@file:Suppress("KDocMissingDocumentation")

object Versions {
    const val targetSdkVersion = 29
    const val compileSdkVersion = 29
    const val minSdkVersion = 19

    const val versionCode = 1
    const val versionName = "1.0.0"

    const val gradle = "3.6.0"
    const val navigation = "2.1.0"

    const val kotlin = "1.3.70"
}

object Plugin {

    const val navigation = "androidx.navigation.safeargs.kotlin"

}

object BuildConfiguration {
    const val applicationId = "com.aceinteract.topcoder.covidstats"

    const val BASE_URL = "\"https://corona-virus-stats.herokuapp.com/api/v1/\""
}

object RootDependencies {
    const val kotlin = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:${Versions.kotlin}"
}

object NetworkDependencies {

    object Versions {
        const val okhttp = "3.12.0"
        const val retrofit = "2.7.0"
    }

    const val okhttp = "com.squareup.okhttp3:okhttp:${Versions.okhttp}"
    const val loggingInterceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.okhttp}"
    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val gsonConverter = "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"
    const val rxJavaAdapter = "com.squareup.retrofit2:adapter-rxjava2:${Versions.retrofit}"
}

object AndroidXDependencies {

    object Versions {
        const val appCompat = "1.1.0-rc01"
        const val constraintLayout = "2.0.0-beta6"
        const val coreKtx = "1.0.2"
        const val lifecycle = "2.0.0"
        const val multiDex = "1.0.3"
        const val navigation = "2.2.0-alpha01"
    }

    const val appCompat = "androidx.appcompat:appcompat:${Versions.appCompat}"
    const val constraintLayout =
        "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
    const val coreKtx = "androidx.core:core-ktx:${Versions.coreKtx}"
    const val lifecycle = "androidx.lifecycle:lifecycle-extensions:${Versions.lifecycle}"
    const val lifecycleCompiler = "androidx.lifecycle:lifecycle-compiler:${Versions.lifecycle}"
    const val multiDex = "com.android.support:multidex:${Versions.multiDex}"
    const val navigationFragmentKtx =
        "androidx.navigation:navigation-fragment-ktx:${Versions.navigation}"
    const val navigationUiKtx = "androidx.navigation:navigation-ui-ktx:${Versions.navigation}"
}

object ViewDependencies {

    object Versions {
        const val androidMaps = "0.5"
        const val coil = "0.11.0"
        const val glide = "4.11.0"
        const val materialComponent = "1.1.0-alpha09"
        const val shimmerLayout = "0.4.0"
    }

    const val androidMaps = "com.google.maps.android:android-maps-utils:${Versions.androidMaps}"
    const val coil = "io.coil-kt:coil:${Versions.coil}"
    const val materialComponent =
        "com.google.android.material:material:${Versions.materialComponent}"
    const val shimmerLayout = "com.facebook.shimmer:shimmer:${Versions.shimmerLayout}"
}

object AsyncDependencies {

    object Versions {
        const val coroutines = "1.3.4"
    }

    const val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"
    const val coroutinesAndroid =
        "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}"

}

object DependencyInjectionDependencies {

    object Versions {
        const val dagger = "2.24"
    }

    const val dagger = "com.google.dagger:dagger:${Versions.dagger}"
    const val daggerAndroidSupport = "com.google.dagger:dagger-android-support:${Versions.dagger}"
    const val daggerCompiler = "com.google.dagger:dagger-compiler:${Versions.dagger}"
    const val daggerAndroidProcessor =
        "com.google.dagger:dagger-android-processor:${Versions.dagger}"
}

object PersistenceDependencies {

    object Versions {
        const val room = "2.1.0"
    }

    const val room = "androidx.room:room-runtime:${Versions.room}"
    const val roomCompiler = "androidx.room:room-compiler:${Versions.room}"
    const val roomKtx = "androidx.room:room-ktx:${Versions.room}"
}

object PlayServicesDependencies {

    object Versions {
        const val googleMaps = "16.1.0"
        const val googleLocation = "16.0.0"
    }

    const val googleMaps = "com.google.android.gms:play-services-maps:${Versions.googleMaps}"
    const val googleLocation =
        "com.google.android.gms:play-services-location:${Versions.googleLocation}"

}

object UtilityDependencies {

    object Versions {
        const val gson = "2.8.5"
        const val timber = "4.7.1"
    }

    const val gson = "com.google.code.gson:gson:${Versions.gson}"
    const val timber = "com.jakewharton.timber:timber:${Versions.timber}"
}

object TestingDependencies {

    object Versions {
        const val roboelectric = "4.3"
        const val mockito = "2.25.0"
        const val jUnit = "4.12"
        const val androidJUnit = "1.1.1"
        const val androidTest = "1.1.0"
        const val androidTestRunner = "1.1.1"
    }

    const val jUnit = "junit:junit:${Versions.jUnit}"
    const val androidJUnit = "androidx.test.ext:junit:${Versions.androidJUnit}"
    const val androidTestRunner = "androidx.test:runner:${Versions.androidTestRunner}"
    const val androidTest = "androidx.test:core:${Versions.androidTest}"
    const val mockitoCore = "org.mockito:mockito-core:${Versions.mockito}"
    const val roboelectric = "org.robolectric:robolectric:${Versions.roboelectric}"
}

object Classpaths {
    const val gradle = "com.android.tools.build:gradle:${Versions.gradle}"
    const val navigation =
        "androidx.navigation:navigation-safe-args-gradle-plugin:${Versions.navigation}"
    const val allopen = "org.jetbrains.kotlin:kotlin-allopen:${Versions.kotlin}"
}