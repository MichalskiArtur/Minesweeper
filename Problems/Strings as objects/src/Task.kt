fun main() {
    val input = readLine()!!
    // write code here

    if (input.isEmpty()) println(input) else {
        if (input[0] == 'i') {
        println(input.drop(1).toInt() + 1)
        } else { if (input[0] == 's') {
            println(input.drop(1).reversed())
            } else println(input)
        }
    }
}