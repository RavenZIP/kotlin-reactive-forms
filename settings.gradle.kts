enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

/** ShortName => krf */
rootProject.name = "KotlinReactiveForms"

pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
    }
}

plugins { id("org.gradle.toolchains.foojay-resolver-convention") version "1.0.0" }

include("krf-core")

include("krf-utils")
