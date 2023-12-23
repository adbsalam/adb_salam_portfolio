open class Module(private val name: String) {
    /**
     * module name as data module e.g ":module:data"
     */
    val data = "${this.name}:data"

    /**
     * module name as feature module e.g ":module:feature"
     */
    val feature = "${this.name}:feature"

    /**
     * module name as module e.g ":module"
     */
    val module = this.name

    /**
     * snapIt path for a feature module
     */
    val snapFeature = "$packageTestPath${this.feature.snapPath()}"

    /**
     * snapIt path for a module
     */
    val snapModule = "$packageTestPath${this.module.snapPath()}"
}

/**
 * Core config module since it contains sub projects with different names
 */
class Core : Module(":core") {
    val prefs = ":core:prefs"
    val network = ":core:network"
}

/**
 * Ui config module since it contains sub projects with different names
 */
class Ui : Module(":ui") {
    val theming = ":ui:theming"
    val components = ":ui:components"
}

/**
 * Convert module name to snapIt path format
 */
private fun String.snapPath() = this.replace(":", "/")

/**
 * Implementation for Gradle Modules
 */
val home = Module(name = ":home")
val info = Module(":info")
val blog = Module(":blog")
val navHost = Module(":navhost")
val startup = Module(":startup")
val settings = Module(":settings")
val gallery = Module(":gallery")
val videos = Module(":videos")
val reviews = Module(":reviews")
val onboarding = Module(":onboarding")
val samples = Module(":samples")
val utils = Module(":utils")
val navigation = Module(":navigation")
val core = Core()
val ui = Ui()