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

        return firsts
            .zip(seconds){ f, s -> s - f }
                .sumOf { abs(it) }
    }

    fun part2(input: List<String>): Int {
        val firsts = mutableListOf<Int>()
        val seconds = mutableListOf<Int>()

        input.forEach {
            val split = it.split(" ")
            firsts.add(split.first().toString().toInt())
            seconds.add(split.last().toString().toInt())
        }

        firsts.sort()
        seconds.sort()

        return firsts.map { n ->
                seconds.filter { n == it }.size * n
            }
            .sum()
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day01_test")
    check(part1(testInput) == 11)
    check(part2(testInput) == 31)

    val input = readInput("Day01")
    println(part1(input))
    println(part2(input))
}