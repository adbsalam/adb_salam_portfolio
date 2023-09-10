import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

fun Project.applyProject(
    module: String,
) {
    this.dependencies {
        "implementation"(project(module))
    }
}