import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
  kotlin("jvm") version "2.0.0"

  id("dev.monosoul.jooq-docker") version "6.0.23"
}

group = "com.example"
version = "0.1"

repositories {
  mavenCentral()
}

dependencies {
  jooqCodegen("org.postgresql:postgresql:42.7.3")

  implementation("org.jooq:jooq:3.19.8")
}

tasks {
  generateJooqClasses {
    basePackageName = "com.example.jooq"

    usingJavaConfig {
      name = "org.jooq.codegen.KotlinGenerator"

      database.apply {
        withName("org.jooq.meta.postgres.PostgresDatabase")
      }

      generate.apply {
        isRecords = true

        // Uncomment this to suppress the annotation that causes the warning.
        // isKotlinSetterJvmNameAnnotationsOnIsPrefix = false
      }
    }
  }
}

jooq {
  withContainer {
    image {
      name = "postgres:15"
    }
  }
}

sourceSets.main {
  java.srcDir("build/generated/kotlin")
}

java {
  toolchain { languageVersion = JavaLanguageVersion.of(21) }
  targetCompatibility = JavaVersion.VERSION_21
}

tasks.withType<KotlinCompile> {
  compilerOptions {
    jvmTarget = JvmTarget.JVM_21
    allWarningsAsErrors = true
  }
}
