import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.android.kotlin.multiplatform.library)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
    alias(libs.plugins.kotlin.serialization)
}

kotlin {
    jvm()

    android {
        namespace = "com.github.ravenzip.kotlin.reactiveforms.demo.shared"
        compileSdk = libs.versions.android.compileSdk.get().toInt()
        minSdk = libs.versions.android.minSdk.get().toInt()

        compilerOptions { jvmTarget = JvmTarget.JVM_17 }

        androidResources { enable = true }

        withHostTest { isIncludeAndroidResources = true }
    }

    js {
        browser()
        binaries.executable()
    }

    @OptIn(ExperimentalWasmDsl::class)
    wasmJs {
        browser()
        binaries.executable()
    }

    @OptIn(ExperimentalKotlinGradlePluginApi::class)
    dependencies {
        implementation(libs.compose.runtime)
        implementation(libs.compose.foundation)
        implementation(libs.compose.material3)
        implementation(libs.compose.ui)
        implementation(libs.compose.components.resources)
        implementation(libs.compose.uiToolingPreview)
        implementation(libs.androidx.lifecycle.viewmodelCompose)
        implementation(libs.androidx.lifecycle.runtimeCompose)
        implementation(libs.material.icons.extended)
        implementation(libs.androidx.lifecycle.viewmodel.navigation3)
        implementation(libs.androidx.navigation3.ui)
        implementation(libs.kotlinx.serialization.json)

        implementation(projects.reactiveformsCore)
        implementation(projects.reactiveformsCompose)
        implementation(libs.ravenzip.bereza.ui.core)

        testImplementation(libs.kotlin.test)
    }
}

dependencies { androidRuntimeClasspath(libs.compose.uiTooling) }
