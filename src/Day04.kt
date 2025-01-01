fun main() {

    fun findHorizontal(input: List<String>) : Int {

        var finalCount = 0
        for (line in input) {
            val matches = Regex("XMAS").findAll(line)
            val matchesReversed = Regex("XMAS").findAll(line.reversed())
            finalCount += matches.count() + matchesReversed.count()
        }

        println(finalCount)
        return finalCount
    }

    fun findVertical(input: List<String>) : Int {
        return 0
    }

    fun part1(input: List<String>): Int {

        return findHorizontal(input)
    }

    fun part2(input: List<String>): Int {
        TODO()
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day04_test")
    check(part1(testInput) == 7)
//    check(part2(testInput) == 5)

    val input = readInput("Day04")
    println(part1(input))
    println(part2(input))
}