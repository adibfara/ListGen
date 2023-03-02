# ListGen - Generate Lists from Annotations!

ListGen is a Kotlin SymbolProcessor (KSP) library that generates lists (also arrays and maps) from
the functions annotated with `@Listed` (or `@Arrayed` and `@Mapped`).

## Usage

First, annotate your functions with your desired output:

- `@Listed` generates `listOf`
- `@Arrayed` generates `arrayOf`
- `@Mapped` generates `mapOf`

All annotations require a name but the `@Mapped` also requires a key.

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

These annotations will generate the following code with the correct imports:

```kotlin
// in build/generated/ksp/debug|main/kotlin/com/snaky/ksp/GeneratedLists.kt
val mainList = listOf(mainModule(), secondModule())
val otherList = listOf(helloModule())

// in build/generated/ksp/debug|main/kotlin/com/snaky/ksp/GeneratedMaps.kt
val firstMap = mapOf("key1" to firstMapTest(), "key2" to firstMapTest2())
```

You can safely use the generated lists, arrays and maps for your needs.

## Installation

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

## Learning KSP

If you want to learn more about KSP, check out
the [official documentation](https://kotlinlang.org/docs/ksp-overview.html).

## Feedback and Bug Reporting

Please share your thoughts on this project
by [filing a Github issue](https://github.com/adibfara/ListGen/issues).

## Inspiration

The initial need for this library emerged when I was
using [Koin](https://github.com/InsertKoinIO/koin) as a dependency injection framework in a project.
You have to pass your list of modules to Koin, and each module is defined somewhere in the project
This results in a file containing `listOf(module1, module2, module3, ...)`, which can get ugly and
error-prone. Using this library to generate that list prevents the file from being ugly and helps
with the Open-Closed Principle for your modules.
