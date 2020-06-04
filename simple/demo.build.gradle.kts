project.ext {
    set("jo", "ja")
}

val helloTask = tasks.register("helloTask") {
    doFirst {
        println("Hello")
    }
}

val goodMorningTask = tasks.register("goodMorningTask") {
    doFirst {
        println("Good Morning")
    }

    shouldRunAfter(helloTask)
}

val seeYouLaterTasks = tasks.register("seeYouTask") {
    doLast {
        println("See you later aligator")
    }
}

val dontForgetTask = task("dontForgetTask") {
    doLast {
        println("And don't forget to smile")
    }
}

val wrap = tasks.create("salute")

wrap.dependsOn(helloTask, goodMorningTask, seeYouLaterTasks, dontForgetTask)
dontForgetTask.shouldRunAfter(seeYouLaterTasks, goodMorningTask, helloTask)


open class Custom : DefaultTask()

val my = tasks.create("my", Custom::class.java)

val perrito = task("perrito", Custom::class) {
    group = "Dogos"
    description = "Dogitos"
    doLast {
        println("Woof!")
        println(project.properties.entries.joinToString(separator = "\n"))
    }
}

open class HasOptions : DefaultTask() {
    @Option(option = "community", description = "Define a community to work with")
    var communityChapter: String = ""

    var cheerMessage = "Vamos"

    @TaskAction
    fun cheer() {
        println("$cheerMessage GDG!")
    }
}

val communityTask = task<HasOptions>("medellinTask") {
    cheerMessage = "YES!"
    doLast {
        println("Android Medellin $communityChapter es lo maximo!")
    }
}


class SierPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        target.tasks.register("holaSoyElTask") {
            group = "Sier Group"
            description = "Hola soy la descripcion"
            doLast {
                println("Hola Soy el task!")
            }
        }
    }
}

apply<SierPlugin>()