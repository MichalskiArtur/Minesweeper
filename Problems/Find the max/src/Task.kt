import java.util.*

fun main(args: Array<String>) {
    val scanner = Scanner(System.`in`)

    val array = IntArray(scanner.nextInt())

    for (i in 0..array.lastIndex) {
        array[i] = scanner.nextInt()
    }

    var index = 0
    var max = array[0]
    for (i in array.indices) {
        if (max < array[i]) {
            max = array[i]
            index = i
        }
    }
    println(index)
}