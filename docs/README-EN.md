# Kotlin Reactive Forms

<div>
<img alt="Kotlin" src="https://img.shields.io/badge/Kotlin-2.2.21-A831F5">
<img alt="Compose Multiplatform" src="https://img.shields.io/badge/Compose Multiplatform-1.9.3-3b83f8">
<img alt="Static Badge" src="https://img.shields.io/badge/API-24+-39ad31">
<a href="https://jitpack.io/#RavenZIP/kotlin-reactive-forms">
    <img alt="Static Badge" src="https://img.shields.io/badge/JitPack-0.1.0-39ad31">
</a>
</div>

> 🌐 **Languages:**  
> [English](README-EN.md) | [Русский](../README.md)

## 🔎 What is Kotlin Reactive Forms??

Kotlin Reactive Forms is a type-safe library of reactive forms and controls in the Kotlin programming language
optimized for Jetpack Compose.

## 🌍 Supported platforms

Coming Soon...

## 🚀 Installation

```
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        ...
        maven ("https://jitpack.io")
    }
}
```

**build.gradle.kts**

```
dependencies {
      implementation("com.github.RavenZIP.kotlin-reactive-forms
:core:$version") 

      // If necessary, you can connect the utils module.
      implementation("com.github.RavenZIP.kotlin-reactive-forms
:utils:$version")
}
```

If you are using libs.versions.toml

**libs.versions.toml**

```
[versions]
ravenzip-kotlin-reactive-forms = "$version"

[libraries]
ravenzip-kotlin-reactive-forms-core = { module = "com.github.RavenZIP.kotlin-reactive-forms:core", version.ref = "ravenzip-kotlin-reactive-forms" }

// If necessary, you can connect the utils module.
ravenzip-kotlin-reactive-forms-utils = { module = "com.github.RavenZIP.kotlin-reactive-forms:utils", version.ref = "ravenzip-kotlin-reactive-forms" }
```

**build.gradle.kts**

```
dependencies {
      implementation(libs.ravenzip.kotlin.reactive.forms.core)
      
      // If necessary, you can connect the utils module.
      implementation(libs.ravenzip.kotlin.reactive.forms.utils)
}
```

## 🚬 Using

Coming Soon...

## 📜 License

This library is licensed under the Apache 2.0 License. See the [LICENSE](../LICENSE) file for details.

## 👾 Developer

**Alexander Chernykh**

- [Telegram](https://t.me/RavenZIP)