sealed class Module(private val name: String) {
    val data = "${this.name}:data"
    val feature = "${this.name}:feature"
    val module = this.name
}

object home : Module(":home")
object info : Module(":info")
object navhost : Module(":navhost")
object startup : Module(":startup")
object settings : Module(":settings")
object gallery : Module(":gallery")
object videos : Module(":videos")
object reviews : Module(":reviews")
object utils : Module(":utils")
object navigation : Module(":navigation")

object core {
    val prefs = ":core:prefs"
    val network = ":core:network"
}

object ui {
    val theming = ":ui:theming"
    val components = ":ui:components"
}