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
        maven { url = java.net.URI("https://devrepo.kakao.com/nexus/content/groups/public/") }

    }
}

rootProject.name = "DolShop"
include(":app")

include(":data")
include(":data:data-mapper")
include(":data:data-mapper:saletodomain")

include(":domain")
include(":presentation")

include(":core:network:product")
include(":core:network:announcement")

include(":macrobenchmark")
include(":data:mapper:saletodomain")
include(":data:data-datasource")

include(":data:data-impl")
include(":data:data-impl:update-productsale-impl")
include(":core:networks")
include(":core:network-product")
include(":designsystem")
