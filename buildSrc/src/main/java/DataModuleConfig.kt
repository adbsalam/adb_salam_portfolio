import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.dsl.LibraryExtension
import com.android.build.api.variant.LibraryAndroidComponentsExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension

private const val COMPILE_SDK = 34
private const val MIN_SDK = 24

class DataModuleConfig(): Plugin<Project>{
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