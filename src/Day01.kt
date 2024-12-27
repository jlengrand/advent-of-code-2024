import kotlin.math.abs

fun main() {
    fun part1(input: List<String>): Int {

        val firsts = mutableListOf<Int>()
        val seconds = mutableListOf<Int>()

        input.forEach {
            val split = it.split(" ")
            firsts.add(split.first().toString().toInt())
            seconds.add(split.last().toString().toInt())
        }

        firsts.sort()
        seconds.sort()

        println(firsts)
        println(seconds)

        val combo = firsts.zip(seconds){ f, s -> s - f }

        return combo.sumOf { abs(it) }
    }

    fun part2(input: List<String>): Int {
        TODO()
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day01_test")
    check(part1(testInput) == 11)
//    check(part2(testInput) == 5)

    val input = readInput("Day01")
    println(part1(input))
//    println(part2(input))
}