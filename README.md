# jOOQ codegen / Kotlin 2.0.0 example

This demonstrates a problem with the jOOQ Kotlin generator's default naming of Record setter methods for fields with names starting with `is` when the code is compiled using Kotlin 2.0.0.

To reproduce:

    ./gradlew classes

This will produce a warning, which is treated as an error since the build has "treat warnings as errors" enabled:

`com/example/jooq/tables/records/FooRecord.kt:33:15 This code uses error suppression for 'INAPPLICABLE_JVM_NAME'. While it might compile and work, the compiler behavior is UNSPECIFIED and WON'T BE PRESERVED.`

To make the code compile, uncomment the `isKotlinSetterJvmNameAnnotationsOnIsPrefix` line in `build.gradle.kts`.
