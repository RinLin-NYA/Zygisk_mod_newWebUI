import com.android.build.gradle.LibraryExtension

plugins {
    id("com.android.application") apply false
    id("com.android.library") apply false
}

buildscript {
    repositories {
        maven("https://plugins.gradle.org/m2/")
    }
    dependencies {
        classpath("org.mozilla.rust-android-gradle:plugin:0.9.3")
    }
}

val verCode by extra(25207)
val verName by extra("25.2-1")
val androidMinSdkVersion by extra(29)
val androidTargetSdkVersion by extra(33)
val androidCompileSdkVersion by extra(33)
val androidBuildToolsVersion by extra("33.0.1")
val androidCompileNdkVersion by extra("25.1.8937393")
val androidSourceCompatibility by extra(JavaVersion.VERSION_11)
val androidTargetCompatibility by extra(JavaVersion.VERSION_11)

tasks.register("Delete", Delete::class) {
    delete(rootProject.buildDir)
}

fun Project.configureBaseExtension() {
    extensions.findByType(LibraryExtension::class)?.run {
        namespace = "icu.nullptr.zygisksu"
        compileSdk = androidCompileSdkVersion
        ndkVersion = androidCompileNdkVersion
        buildToolsVersion = androidBuildToolsVersion

        defaultConfig {
            minSdk = androidMinSdkVersion
            targetSdk = androidTargetSdkVersion
        }

        lint {
            abortOnError = true
        }
    }
}

subprojects {
    plugins.withId("com.android.library") {
        configureBaseExtension()
    }
}