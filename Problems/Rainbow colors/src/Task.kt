import java.util.Scanner

fun main(args: Array<String>) {
    val input = Scanner(System.`in`)
    val color = input.nextLine()

    fun isRainbow(color: String): Boolean {
        for (enum in Rainbow.values()) {
            if (color.toUpperCase() == enum.name) return true
        }
        return false
    }

    println(isRainbow(color))
}

enum class Rainbow(val color: String) {
    RED("Red"),
    ORANGE("Orange"),
    YELLOW("Yellow"),
    GREEN("Green"),
    BLUE("Blue"),
    INDIGO("Indigo"),
    VIOLET("Violet")
}