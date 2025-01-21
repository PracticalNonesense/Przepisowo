
pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
    plugins {
        val kotlinVersion = "2.0.0"
        kotlin("jvm") version kotlinVersion apply false
        id("org.jetbrains.kotlin.android") version kotlinVersion apply false
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "CookBook_App"
include(":app")
