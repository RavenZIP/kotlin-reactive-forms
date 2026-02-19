# Kotlin Reactive Forms

<div>
<img alt="Kotlin" src="https://img.shields.io/badge/Kotlin-2.3.10-A831F5">
<img alt="Compose Multiplatform" src="https://img.shields.io/badge/Compose Multiplatform-1.10.1-3b83f8">
<img alt="Static Badge" src="https://img.shields.io/badge/API-24+-39ad31">
<a href="https://jitpack.io/#RavenZIP/kotlin-reactive-forms">
  <img src="https://jitpack.io/v/RavenZIP/kotlin-reactive-forms.svg">
</a>
</div>

> 🌐 **Languages:**  
> [Русский](README.md) | [English](docs/README-EN.md)

## 🔎 Что такое Kotlin Reactive Forms?

Kotlin Reactive Forms - это типобезопасная библиотека реактивных форм и контролов на языке программирования Kotlin
с оптимизацией под Jetpack Compose.

## 🌍 Поддерживаемые платформы

| Платформа | Статус              |
|-----------|---------------------|
| Windows   | ✅ Поддерживается    |
| Linux     | ❓ Неизвестно        |
| macOS     | ❓ Неизвестно        |
| Web       | ✅ Поддерживается    |
| Android   | ✅ Поддерживается    |
| iOS       | ❌ Не поддерживается |

Linux, macOS, iOS временно не поддерживаются, так как невозможно проверить
работоспособность библиотеки на данной платформе

## 🚀 Установка

**settings.gradle.kts**

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
      implementation("com.github.RavenZIP.kotlin-reactive-forms:krf-core:$version") 

      // При необходимости можно подключить utils-модуль
      implementation("com.github.RavenZIP.kotlin-reactive-forms:krf-utils:$version")
}
```

Если вы используете libs.versions.toml

**libs.versions.toml**

```
[versions]
ravenzip-kotlin-reactive-forms = "$version"

[libraries]
ravenzip-kotlin-reactive-forms-core = { module = "com.github.RavenZIP.kotlin-reactive-forms:krf-core", version.ref = "ravenzip-kotlin-reactive-forms" }

// При необходимости можно подключить utils-модуль
ravenzip-kotlin-reactive-forms-utils = { module = "com.github.RavenZIP.kotlin-reactive-forms:krf-utils", version.ref = "ravenzip-kotlin-reactive-forms" }
```

**build.gradle.kts**

```
dependencies {
      implementation(libs.ravenzip.kotlin.reactive.forms.core)
      
      // При необходимости можно подключить utils-модуль
      implementation(libs.ravenzip.kotlin.reactive.forms.utils)
}
```

## 🚬 Использование

Скоро...

## 📜 Лицензия

Эта библиотека распространяется по лицензии Apache 2.0. Подробности смотрите в файле [ЛИЦЕНЗИЯ](LICENSE).

## 👾 Разработчик

**Черных Александр**

- [Telegram](https://t.me/RavenZIP)
