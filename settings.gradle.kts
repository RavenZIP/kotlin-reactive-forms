enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

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
        maven("https://jitpack.io")
    }
}

plugins { id("org.gradle.toolchains.foojay-resolver-convention") version "1.0.0" }

// TODO переименовать модули: reactive-forms-core, reactive-forms-compose и reactive-forms-utils
/** Библиотечные модули, доступные публично */
include("reactiveforms-core", "reactiveforms-compose", "reactiveforms-utils")

/** Библиотечные модули, недоступные публично */
include(
    "reactive-forms-demo:shared",
    "reactive-forms-demo:desktopApp",
    "reactive-forms-demo:androidApp",
    "reactive-forms-demo:webApp",
)
