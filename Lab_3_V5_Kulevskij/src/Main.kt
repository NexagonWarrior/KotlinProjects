import kotlin.random.Random

const val SIZE = 10

// Клас для моделювання гри
class GameOfLife {
    private var field: Array<Array<Boolean>> = Array(SIZE) { Array(SIZE) { false } }
    private val history: MutableList<Array<Array<Boolean>>> = mutableListOf()
    private var stepCount: Int = 0

    // Метод для відображення поля
    fun printField() {
        println("Крок: $stepCount")
        println("  A B C D E F G H I J")
        for (i in field.indices) {
            print("${i} ")
            for (j in field[i].indices) {
                print(if (field[i][j]) "*" else "_")
            }
            println()
        }
    }

    // Метод для скидання гри
    fun reset() {
        field = Array(SIZE) { Array(SIZE) { false } }
        history.clear()
        stepCount = 0
        println("Гру скинуто.")
    }

    // Метод для генерації N випадкових організмів
    fun setRandomOrganisms(N: Int) {
        repeat(N) {
            val x = Random.nextInt(SIZE)
            val y = Random.nextInt(SIZE)
            field[x][y] = true
        }
        println("Згенеровано $N організмів.")
    }

    // Метод для очищення поля
    fun clear() {
        field = Array(SIZE) { Array(SIZE) { false } }
        println("Поле очищено.")
    }

    // Метод для кроку вперед
    fun step(steps: Int = 1) {
        repeat(steps) {
            history.add(copyField())
            val newField = Array(SIZE) { Array(SIZE) { false } }
            for (i in field.indices) {
                for (j in field[i].indices) {
                    val aliveNeighbors = countAliveNeighbors(i, j)
                    newField[i][j] = when {
                        field[i][j] && (aliveNeighbors == 2 || aliveNeighbors == 3) -> true
                        !field[i][j] && aliveNeighbors == 3 -> true
                        else -> false
                    }
                }
            }
            field = newField
            stepCount++
            if (!hasChanges()) {
                println("Конфігурація не змінилась. Гра завершена.")
                return
            }
        }
    }

    // Метод для кроку назад
    fun back(steps: Int = 1) {
        repeat(steps) {
            if (history.isNotEmpty()) {
                field = history.removeAt(history.size - 1)
                stepCount--
            } else {
                println("Не можна повернутися назад. Це перший крок.")
                return
            }
        }
    }

    // Метод для підрахунку живих сусідів
    private fun countAliveNeighbors(x: Int, y: Int): Int {
        val directions = listOf(
            -1 to -1, -1 to 0, -1 to 1,
            0 to -1,          0 to 1,
            1 to -1, 1 to 0, 1 to 1
        )
        var count = 0
        for ((dx, dy) in directions) {
            val nx = (x + dx + SIZE) % SIZE
            val ny = (y + dy + SIZE) % SIZE
            if (field[nx][ny]) count++
        }
        return count
    }

    // Метод для копіювання поля
    private fun copyField(): Array<Array<Boolean>> {
        return Array(SIZE) { i -> Array(SIZE) { j -> field[i][j] } }
    }

    // Метод для перевірки, чи є зміни в полі
    private fun hasChanges(): Boolean {
        return field.any { row -> row.contains(true) }
    }
}

fun main() {
    val game = GameOfLife()
    game.printField()

    while (true) {
        println("Команди: reset, set N, clear, step N, back N")
        print("Введіть команду: ")
        val input = readLine()?.trim() ?: continue
        val command = input.split(" ")

        when (command[0]) {
            "reset" -> game.reset()
            "set" -> {
                val N = command.getOrNull(1)?.toIntOrNull() ?: continue
                if (N in 2..99) game.setRandomOrganisms(N)
            }
            "clear" -> game.clear()
            "step" -> {
                val steps = command.getOrNull(1)?.toIntOrNull() ?: 1
                game.step(steps)
            }
            "back" -> {
                val steps = command.getOrNull(1)?.toIntOrNull() ?: 1
                game.back(steps)
            }
            else -> println("Невідома команда.")
        }
        game.printField()
    }
}
