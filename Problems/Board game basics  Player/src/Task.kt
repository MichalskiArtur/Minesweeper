import kotlin.random.Random

class Player(val id: Int, val name: String, val hp: Int) {

    companion object {

        fun create(name: String): Player {

            val id = Random.nextInt(0, 100)
            return Player(id, name, 100)
        }
    }
}