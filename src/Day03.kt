fun main() {
    fun part1(input: List<String>): Int {

        val full = input.joinToString("")

        val regex = """mul\((\d{1,3}),(\d{1,3})\)""".toRegex()
        val matches = regex.findAll(full)

        val results = matches.map { it.groupValues[1] to it.groupValues[2] }.toList()

        return results.sumOf {  it.second.toInt() * it.first.toInt() }
    }

    fun part2(input: List<String>): Int {
        TODO()
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day03_test")
    check(part1(testInput) == 161)
//    check(part2(testInput) == 5)

    val input = readInput("Day03")
    println(part1(input))
//    println(part2(input))
}