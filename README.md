# ListGen, Generate Lists From Functions That Have @Listed Annotations!

Welcome to ListGen!

ListGen is a KSP-based library that can generate lists (and also arrays and maps)
from your functions that are annotated with `@Listed` (also `@Arrayed` and `@Mapped`).

# Usage

First, annotate your functions with your desired output. You can use `@Listed` to generate `listOf`
, `@Arrayed` to generate `arrayOf` and `@Mapped` for generating `mapOf`.

All annotations require a name and `@Mapped` requires a key.

Let's see it in action:

```kotlin 

// can be anywhere (any file) in your module
@Listed("mainList")
fun mainModule() = 2

// can be anywhere (any file) in your module
@Listed("otherList")
fun helloModule() = "hello!"

// can be anywhere (any file) in your module
@Listed("mainList")
fun secondModule() = 3

```

The code above will generate the following code with correct imports:

```kotlin
// in build/generated/ksp/debug|main/kotlin/com/snaky/ksp/GeneratedLists.kt
val mainList = listOf(mainModule(), secondModule())
val otherList = listOf(helloModule())

// in build/generated/ksp/debug|main/kotlin/com/snaky/ksp/GeneratedMaps.kt
val firstMap = mapOf("key1" to firstMapTest(), "key2" to firstMapTest2())
```

You can safely use the generated lists, arrays and maps for your needs.

# Installation

Add the following to your build.gradle (or build.gradle.kts)

```groovy
plugins {
    id 'com.google.devtools.ksp' version '1.6.20-1.0.5'
    // the version according to your Kotlin and KSP
}

// to add support for IDE
android {
    kotlin {
        sourceSets.debug {
            kotlin.srcDirs += 'build/generated/ksp/debug/kotlin'
        }
        sourceSets.release {
            kotlin.srcDirs += 'build/generated/ksp/release/kotlin'
        }
    }
}

dependencies {
    implementation "io.github.adibfara.listgen:ksp:1.0.0"
    ksp "io.github.adibfara.listgen:ksp:1.0.0"
}
```

# Learning KSP

If you want to learn more about KSP, most of the documentation of KSP can be found
on [kotlinlang.org](https://kotlinlang.org/docs/ksp-overview.html).

## Feedback and Bug Reporting

Please let me know what you think about this project
by [filing a Github issue](https://github.com/adibfara/ListGen/issues)

## Notes

The initial need for this library emerged when I was using Koin in a project. You have to pass your
list of modules to Koin and each module is defined somewhere in the project and you'll have a file
containing `listOf(module1, module2, module3, ...)` which can get ugly (and error-prone).

Using this library to generate that list can help not only the file being ugly, but also help with
Open/Closed Principle for your modules.

