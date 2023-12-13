import org.jetbrains.compose.ExperimentalComposeLibrary
import org.jetbrains.kotlin.gradle.plugin.mpp.apple.XCFramework

plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.jetbrains.compose)
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.kotlinSerialization)
    alias(libs.plugins.kt.lint.gradle)
    `maven-publish`
}

kotlin {
    applyDefaultHierarchyTemplate()

    androidTarget {
        publishAllLibraryVariants()
        compilations.all {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }
    }

    val xcf = XCFramework()
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "kmp-launchpad-compose"
            xcf.add(this)
        }
    }

    sourceSets {
        androidMain.dependencies {
            implementation(compose.material3)
            implementation(libs.androidx.navigation.compose)
        }
        commonMain.dependencies {
            implementation(compose.animation)
            @OptIn(ExperimentalComposeLibrary::class)
            implementation(compose.components.resources)
            implementation(compose.foundation)
            implementation(compose.material3)
            implementation(compose.runtime)
            implementation(compose.ui)
        }
        commonTest.dependencies {
            implementation(kotlin("test-common"))
            implementation(kotlin("test-annotations-common"))
            implementation(libs.kotlin.test)
        }
    }
}

android {
    namespace = "com.bottlerocketstudios.launchpadcompose"
    compileSdk = libs.versions.compile.sdk.get().toInt()
    defaultConfig {
        minSdk = libs.versions.min.sdk.get().toInt()
    }
}

ktlint {
    verbose.set(true)
    outputToConsole.set(true)
    ignoreFailures.set(true)
}

group = "com.github.bottlerocketstudios"
version = "${libs.versions.launchpad.compose.get()}-SNAPSHOT"

publishing {
    repositories {
        maven {
            name = "GitHubPackages"
            url = uri("https://maven.pkg.github.com/BottleRocketStudios/KMP-LaunchPad-Compose")
            credentials {
                username = System.getenv("GITHUB_ACTOR")
                password = System.getenv("GITHUB_TOKEN")
            }
        }
    }
}
