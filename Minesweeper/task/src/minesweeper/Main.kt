package minesweeper

import kotlin.random.Random

object Game {

    enum class CellType {
        FREE,
        MINE
    }

    private class Field(
            private val width: Int,
            private val height: Int,
            private val minesNumber: Int
    ) {

        //private class Cell(var mines: Int = 0, var marked: Boolean = false)
        private class Cell(var mines: Int = 0,
                           var marked: Boolean = false,
                           var explored: Boolean = false)

        private lateinit var field: Array<Array<Cell>>

        init {
            initEmptyField(width, height)
            generateMines()
            countMines()
        }

        private fun initEmptyField(width: Int, height: Int) {
            field = Array(height) { Array(width) { Cell() } }
        }

        private fun generateMines() {
            repeat(minesNumber) {
                var x: Int
                var y: Int
                do {
                    x = Random.nextInt(width)
                    y = Random.nextInt(height)
                } while (field[x][y].mines == -1)
                field[x][y].mines = -1
            }
        }

        fun replaceMine(x: Int, y: Int) {

            var newX: Int
            var newY: Int
            do {
                newX = Random.nextInt(width)
                newY = Random.nextInt(height)
            } while (field[newX][newY].mines == -1)
            field[x][y].mines = 0
            cleanMineNubmers()
            countMines()

        }

        fun cleanMineNubmers() {
            for (i in 0 until width) {
                for (j in 0 until height) {
                    if (field[i][j].mines != -1 ) {
                       field[i][j].mines = 0
                    }
                }
            }
        }

        private fun incrementCell(i: Int, j: Int) {
            if (field[i][j].mines != -1) field[i][j].mines++
        }

        private fun lookAround(i: Int, j: Int) {
            if (i - 1 >= 0 && j - 1 >= 0) incrementCell(i - 1, j - 1)
            if (i - 1 >= 0) incrementCell(i - 1, j)
            if (i - 1 >= 0 && j + 1 < width) incrementCell(i - 1, j + 1)
            if (j - 1 >= 0) incrementCell(i, j - 1)
            if (j + 1 < width) incrementCell(i, j + 1)
            if (i + 1 < height && j - 1 >= 0) incrementCell(i + 1, j - 1)
            if (i + 1 < height) incrementCell(i + 1, j)
            if (i + 1 < height && j + 1 < width) incrementCell(i + 1, j + 1)
        }

        private fun clearWrongBomb(i: Int, j: Int) {
            if (i - 1 >= 0 && j - 1 >= 0) unmarkCell(i - 1, j - 1)
            if (i - 1 >= 0) unmarkCell(i - 1, j)
            if (i - 1 >= 0 && j + 1 < width) unmarkCell(i - 1, j + 1)
            if (j - 1 >= 0) unmarkCell(i, j - 1)
            if (j + 1 < width) unmarkCell(i, j + 1)
            if (i + 1 < height && j - 1 >= 0) unmarkCell(i + 1, j - 1)
            if (i + 1 < height) unmarkCell(i + 1, j)
            if (i + 1 < height && j + 1 < width) unmarkCell(i + 1, j + 1)
        }

        private fun countMines() {
            for (i in 0 until width) {
                for (j in 0 until height) {
                    if (field[i][j].mines == -1) {
                        lookAround(i, j)
                    }
                }
            }
        }

        fun checkNoBombArea(i: Int, j: Int) {
            if (!field[i][j].explored && field[i][j].mines == 0) {
                exploreCell(i, j)
                clearWrongBomb(i, j)
                if (i - 1 >= 0 && j - 1 >= 0) {
                    if (numberMines(i - 1, j - 1) == 0) checkNoBombArea(i - 1, j - 1) else exploreCell(i - 1, j - 1)
                }
                if (i - 1 >= 0) {
                    if (numberMines(i - 1, j) == 0) checkNoBombArea(i - 1, j) else exploreCell(i - 1, j)
                }
                if (i - 1 >= 0 && j + 1 < width) {
                    if (numberMines(i - 1, j + 1) == 0) checkNoBombArea(i - 1, j + 1) else exploreCell(i - 1, j + 1)
                }
                if (j - 1 >= 0) {
                    if (numberMines(i, j - 1) == 0) checkNoBombArea(i, j - 1) else exploreCell(i, j - 1)
                }
                if (j + 1 < width) {
                    if (numberMines(i, j + 1) == 0) checkNoBombArea(i, j + 1) else exploreCell(i, j + 1)
                }
                if (i + 1 < height && j - 1 >= 0) {
                    if (numberMines(i + 1, j - 1) == 0) checkNoBombArea(i + 1, j - 1) else exploreCell(i + 1, j - 1)
                }
                if (i + 1 < height) {
                    if (numberMines(i + 1, j) == 0) checkNoBombArea(i + 1, j) else exploreCell(i + 1, j)
                }
                if (i + 1 < height && j + 1 < width) {
                    if (numberMines(i + 1, j + 1) == 0) checkNoBombArea(i + 1, j + 1) else exploreCell(i + 1, j + 1)
                }
            }
            exploreCell(i, j)
        }

        fun endDraw() {
            println(" |123456789|")
            println("-|---------|")
            for (j in 0 until height) {
                print("${j + 1}|")
                for (i in 0 until width) {
                    if (field[i][j].marked) {
                        print("*")
                    } else {
                        when (field[i][j].mines) {
                            0 -> {
                                print("/")
                            }
                            1, 2, 3, 4, 5, 6, 7, 8 -> {
                                print("${field[i][j].mines}")
                            }
                            else -> {
                                print("X")
                            }
                        }
                    }
                    }

                        println("|")
                    }
                    println("-|---------|")
        }

        fun draw() {
            println(" |123456789|")
            println("-|---------|")
            for (j in 0 until height) {
                print("${j + 1}|")
                for (i in 0 until width) {
                    if (field[i][j].marked) {
                        print("*")
                    } else if (field[i][j].explored) {

                        when (field[i][j].mines) {
                            0 -> {
                                print("/")
                            }
                            1, 2, 3, 4, 5, 6, 7, 8 -> {
                                print("${field[i][j].mines}")
                            }
                            else -> {
                                print("X")
                            }
                        }
                    } else { print(".")}
                }
                println("|")
            }
            println("-|---------|")
        }

        fun numberMines(x: Int, y: Int): Int {
            val cell = field[x][y]
            return cell.mines
        }

        fun exploreCell(x: Int, y: Int) {
            val cell = field[x][y]
            cell.explored = true
        }

        fun unmarkCell(x: Int, y: Int) {
            val cell = field[x][y]
            if (cell.marked) {
                cell.marked = false
            }
        }

        fun markCell(x: Int, y: Int) {
            val cell = field[x][y]
            cell.marked = !cell.marked
        }


        fun allMinesMarked(): Boolean {
                for (i in 0 until width) {
                for (j in 0 until height) {
                    if (!(field[i][j].marked && field[i][j].mines == -1 ||
                                    !field[i][j].marked && field[i][j].mines != -1)) {
                        return false
                    }
                }
            }
            return true
        }

    }



    private const val startMessage = "How many mines do you want on the field?"
    private const val height = 9
    private const val width = 9

    private var minesNumber: Int = 0
    private lateinit var field: Field

    private fun showStartMessage() {
        println(startMessage)
    }

    private var firstFree = true
    var gameEnd = false

    fun start() {
        showStartMessage()
        readMinesNumber()
        field = Field(width, height, minesNumber)
        field.draw()

        while (!field.allMinesMarked() && !gameEnd) {
            val (x, y, cellType) = askForCoordinates()
            if (cellType == CellType.MINE ) {
                field.markCell(x, y)
                field.draw()
            } else {
                    if (field.numberMines(x, y) == -1) {
                        if (firstFree) {
                            firstFree = false
                            field.replaceMine(x, y)
                            field.draw()


                        }
                        else {
                            println("You stepped on a mine and failed!")
                            gameEnd = true
                            field.endDraw()
                        }
                    } else {
                        firstFree = false
                        field.checkNoBombArea(x, y)
                        field.draw()
                    }
            }
        }
        println("Congratulations! You found all the mines!")
    }


    private fun askForCoordinates(): Triple<Int, Int, CellType> {
        println("Set/delete mines marks (x and y coordinates): ")
        val line = readLine()!!.split(" ")
        val x = line[0].toInt()
        val y = line[1].toInt()
        val cellType = when (line[2]) {
            "free" -> CellType.FREE
            "mine" -> CellType.MINE
            else -> throw IllegalArgumentException("unknown cell type ${line[2]}")
        }
        return Triple(x -1 , y -1 , cellType)
    }

    private fun readMinesNumber() {
        minesNumber = readLine()!!.toInt()
    }
}


fun main() {
    Game.start()
}

