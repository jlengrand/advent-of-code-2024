fun main() {
    fun part1(input: List<String>): Int {

        val full = input.joinToString("")

        val regex = """mul\((\d{1,3}),(\d{1,3})\)""".toRegex()
        val matches = regex.findAll(full)

        val results = matches.map { it.groupValues[1] to it.groupValues[2] }.toList()

        return results.sumOf {  it.second.toInt() * it.first.toInt() }
    }

    fun part2(input: List<String>): Int {
        val full = input.joinToString("")

        val ranges = mutableListOf<String>()
        var current = full
        while(current.isNotEmpty()) {

            val donts = current.indexOf("don't()")

            if(donts != -1) {
                ranges.add(current.substring(0, donts))
            }
            else{
                ranges.add(current)
                break
            }

            current = current.substring(donts + 7)

            val dos = current.indexOf("do()")

            if(dos == -1) {
                break
            }
            else{
                current = current.substring(dos + 4)
            }
        }

        println(ranges)
        val final = ranges.joinToString("")

        val regex = """mul\((\d{1,3}),(\d{1,3})\)""".toRegex()
        val matches = regex.findAll(final)

        val results = matches.map { it.groupValues[1] to it.groupValues[2] }.toList()

        return results.sumOf {  it.second.toInt() * it.first.toInt() }
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day03_test")
    check(part1(testInput) == 161)
    check(part2(readInput("Day03_test2")) == 48)

    val input = readInput("Day03")
    println(part1(input))
    println(part2(input))
}