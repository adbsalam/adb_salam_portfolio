import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

/**
 * Current Compile SDK version of module
 */
const val COMPILE_SDK = 34

/**
 * Current Minimum SDK version of module
 */
const val MIN_SDK = 24

/**
 * Compose compiler version, Since cannot be inherited from version catalogue
 */
const val COMPOSE_COMPILER = "1.4.7"

/**
 * Build flavor debug as default debug flavor
 */
const val DEBUG_FLAVOR = "debug"

/**
 * package test path for snapIt path setup
 */
const val packageTestPath = "src/test/java/uk/adbsalam/portfolio/"

/**
 * Simplify project implementation command
 */
fun Project.applyProject(module: String) {
    this.dependencies {
        "implementation"(project(module))
    }
}

/**
 * Simplify api implementation command
 */
fun Project.apiProject(module: String) {
    this.dependencies {
        "api"(project(module))
    }
}

/**
 * get name space along with package ID
 */
fun asNameSpace(name: String): String {
    return "uk.adbsalam.portfolio.${name}"
}