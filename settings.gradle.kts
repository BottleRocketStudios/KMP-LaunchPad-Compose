enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
pluginManagement {
    repositories {
        maven {
            url = uri("https://maven.pkg.github.com/BottleRocketStudios/KMP-LaunchPad-Utils-Domain")
            // NOTE: To retrieve QSRKit private assets, Github Actions use GITHUB_ACTOR and GITHUB_TOKEN, so set those env vars first.
            // For local builds, they will be null, so user env vars QSRKIT_USERNAME and QSRKIT_TOKEN will be used.
            credentials {
                username = System.getenv("QSRKIT_USERNAME") ?: System.getenv("QSRKIT_USERNAME")
                password = System.getenv("QSRKIT_TOKEN") ?: System.getenv("QSRKIT_TOKEN")
            }
        }
        google()
        gradlePluginPortal()
        mavenCentral()
    }
}

dependencyResolutionManagement {
    repositories {
        maven {
            url = uri("https://maven.pkg.github.com/BottleRocketStudios/KMP-LaunchPad-Utils-Domain")
            credentials {
                username = System.getenv("QSRKIT_USERNAME")
                password = System.getenv("QSRKIT_TOKEN")
            }
        }
//        mavenLocal()

        google()
        mavenCentral()
    }
}

rootProject.name = "Launchpad_Compose"
include(":kmp-launchpad-compose")