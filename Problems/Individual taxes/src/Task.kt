import java.util.*

fun main(args: Array<String>) {
    val scanner = Scanner(System.`in`)

    val size = scanner.nextInt()

    val per = IntArray(size)
    val incom = IntArray(size)

    for (i in 0..incom.lastIndex) {
        incom[i] = scanner.nextInt()
    }

    for (i in 0..per.lastIndex) {
        per[i] = scanner.nextInt()
    }

    var index = 0
    var max = incom[0] * per[0]

    for (i in 0..per.lastIndex) {
        if (max < incom[i] * per[i]) {
            max = incom[i] * per[i]
            index = i
        }
    }
    println(index + 1)
}