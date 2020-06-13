import java.util.*

fun main(args: Array<String>) {
    val scanner = Scanner(System.`in`)

    val array = IntArray(scanner.nextInt())

    for (i in 0..array.lastIndex) {
        array[i] = scanner.nextInt()
    }
    val temp = array[array.lastIndex]
    for (i in array.lastIndex downTo 1) {
        array[i] = array[i - 1]
    }
    array[0] = temp
    for (i in 0..array.lastIndex) {
        print("${array[i]} ")
    }
}