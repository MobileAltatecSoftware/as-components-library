# 📦 AS Components Library

**AS Components** is a reusable component library for Android applications, developed in Kotlin using Jetpack Compose.  
It is maintained by [AltatecSoftware](https://github.com/MobileAltatecSoftware) and designed to simplify UI development by providing modular, plug-and-play components that follow modern Android best practices.

---

## 🚀 Features

- Ready-to-use Jetpack Compose components
- Clean architecture-friendly structure
- Support for light and dark themes
- Easy to integrate, extend, and customize
- Ideal for RFID-related UIs and general app interfaces

---

## 🧩 Included Components

- `AppBottomSheet`: Reusable modal with selectable actions
- `BatteryPercentage`: Displays the current battery level
- `CustomTopBar`: Customizable top app bar
- `FileNameDialog`: Input dialog for filenames
- `InfoCard` / `InfoCardItem`: Labeled info display cards
- `LogoBackground`: Branded background component
- `RfidBottomBar`: Navigation bar for RFID features
- `RfidInventoryCard`: Inventory card UI for RFID scans
- `RfidSearchCard`: Card UI for RFID search results
- `RfidTopBar`: App bar customized for RFID features
- `SelectFileItem`: List item for selectable file display

---

## 🧱 Tech Stack

| Tool                  | Version         |
|-----------------------|-----------------|
| **AGP**               | 8.10.1          |
| **Kotlin**            | 2.0.21          |
| **Jetpack Compose BOM** | 2024.09.00    |
| **Compile SDK**       | 35              |
| **Min SDK**           | 26              |
| **Java Version**      | 11              |

---

## 📦 Installation

If using [JitPack](https://jitpack.io), add the following to your **Kotlin DSL Gradle files**:

### 1. Root `settings.gradle.kts`

```kotlin
dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
        maven(url = "https://jitpack.io")
    }
}

dependencies {
    implementation("com.github.MobileAltatecSoftware:as-components-library:1.0.0")
}
