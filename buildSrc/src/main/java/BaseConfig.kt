import com.android.build.api.dsl.LibraryExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project

/**
 * Base Config for Gradle
 * Minimal config implementation
 *
 * Apply default config for module here
 * such as sdk levels and compile options
 */
class BaseConfig: Plugin<Project>{

    override fun apply(project: Project) {
        val android = project.extensions.getByType(LibraryExtension::class.java)

        android.compileSdk = COMPILE_SDK
        android.defaultConfig.minSdk = MIN_SDK

        android.compileOptions {
            sourceCompatibility = JavaVersion.VERSION_17
            targetCompatibility = JavaVersion.VERSION_17
        }
    }
}