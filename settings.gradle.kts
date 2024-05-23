pluginManagement {
  repositories {
    mavenCentral()
    gradlePluginPortal()
  }
}

plugins {
  // Automatically downloads the correct JDK version if it's not installed locally.
  id("org.gradle.toolchains.foojay-resolver-convention") version ("0.5.0")
}

rootProject.name = "jooq-kotlin-2-example"
