import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
    alias(libs.plugins.vanniktech.mavenPublish)
    alias(libs.plugins.android.kotlin.multiplatform.library)
}

version = "0.1.0"

kotlin {
    jvm()

    androidLibrary {
        namespace = "org.jetbrains.kotlinx.kotlin.reactive.forms.utils"
        compileSdk = libs.versions.android.compileSdk.get().toInt()
        minSdk = libs.versions.android.minSdk.get().toInt()

        compilations.configureEach { compilerOptions.configure { jvmTarget.set(JvmTarget.JVM_17) } }
    }

    @OptIn(ExperimentalWasmDsl::class)
    wasmJs {
        outputModuleName.set("kotlin-reactive-forms-utils")
        browser { commonWebpackConfig { outputFileName = "kotlin-reactive-forms-utils.js" } }
    }

    sourceSets {
        commonMain.dependencies {
            implementation(project(":core"))
            implementation(compose.runtime)
            implementation(libs.kotlinx.coroutines)
        }

        commonTest.dependencies {
            implementation(libs.kotlin.test)
            implementation(libs.turbine)
            implementation(libs.kotlinx.coroutines.test)
        }
    }
}

publishing {
    publications {
        register<MavenPublication>("release") {
            groupId = "io.github.ravenzip.kotlin-reactive-forms"
            artifactId = "kotlin-reactive-forms-utils"
            version = "0.1.0"

            afterEvaluate { from(components["kotlin"]) }
        }
    }
}
