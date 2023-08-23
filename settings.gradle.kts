pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "Clozit Haven"
include(":app")
include(":local")
include(":remote")
include(":repository")
include(":useCase")
include(":entity")
include(":ui")
include(":viewModel")
