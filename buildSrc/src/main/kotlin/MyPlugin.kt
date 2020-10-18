import com.android.build.gradle.AppExtension
import org.gradle.api.Plugin
import org.gradle.api.Project

class MyPlugin : Plugin<Project> {

    override fun apply(project: Project) {
        println("=======project========" + project.name)
        project.tasks.create("myHello") {
            println("=======hello========" + "hello say")
        }
        project.afterEvaluate {
            val extension = project.extensions.getByName("android") as AppExtension
            extension.applicationVariants.forEach { variant ->
                println("=======variant.name========" + variant.name)

                val assembleTaskName = "assemble${variant.name.capitalize()}"
                task("${assembleTaskName}Rasp") {
                    group = "rasp"
                    description = "Wrap the ${variant.name} build with RASP"
                }
            }
        }
    }
}