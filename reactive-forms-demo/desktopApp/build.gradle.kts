import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    alias(libs.plugins.kotlinJvm)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
}

kotlin {
    dependencies {
        implementation(compose.desktop.currentOs)
        implementation(libs.kotlinx.coroutines.swing)

        implementation(projects.reactiveFormsDemo.shared)
    }
}

compose.desktop {
    application {
        mainClass = "com.github.ravenzip.kotlin.reactiveforms.MainKt"

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "kotlin-reactiveforms-demo"
            packageVersion = "1.0.0"
        }
    }
}
