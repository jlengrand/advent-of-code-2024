import kotlin.math.abs

fun main() {

    fun safeIncreaseOrDecrease(report : List<Int>) : Boolean {
        return report.sorted() == report
                || report.sorted() == report.reversed()
    }

    fun safeClose(report : List<Int>) : Boolean {
        return report.zipWithNext().map { it.second - it.first }.all { abs(it) in 1..3 }
    }

    fun safe(report : List<Int>) : Boolean {
        return safeIncreaseOrDecrease(report) && safeClose(report)
    }

    fun almostAlwaysIncrease(report : List<Int>) : Boolean {
        val diffs =  report.zipWithNext().map { it.second - it.first }

        if (diffs.all { it > 0 }) return true

        val idx = diffs.indexOfFirst { it <= 0 }

        val shorterDiffs = diffs.toMutableList()
        shorterDiffs.removeAt(idx)

        if (shorterDiffs.all { it > 0 }) return true

        return false
    }

    fun almostAlwaysDecrease(report : List<Int>) : Boolean {
        val diffs =  report.zipWithNext().map { it.second - it.first }

        if (diffs.all { it < 0 }) return true

        val idx = diffs.indexOfFirst { it >= 0 }

        val shorterDiffs = diffs.toMutableList()
        shorterDiffs.removeAt(idx)

        if (shorterDiffs.all { it < 0 }) return true

        return false
    }

    fun almostSafeIncreaseOrDecrease(report : List<Int>) : Boolean {
        return almostAlwaysDecrease(report) || almostAlwaysIncrease(report)
    }

    fun almostSafeClose(report : List<Int>) : Boolean {
        val diffs =  report.zipWithNext().map { it.second - it.first }

        if (diffs.all { abs(it) in 1..3 }) return true


        val idx = diffs.indexOfFirst { abs(it) !in 1..3 }

        val shorterReport = report.toMutableList()
        shorterReport.removeAt(idx)
        val shorterDiffs = shorterReport.zipWithNext().map { it.second - it.first }

        if (shorterDiffs.all { abs(it) in 1..3 }) return true

        return false
    }

    fun almostSafe(report : List<Int>) : Boolean {
        return almostSafeIncreaseOrDecrease(report) && almostSafeClose(report)
    }

    fun part1(input: List<String>): Int {
        val reports = input.map { line -> line.split(" ").map { it.toInt() } }
        return reports.filter { safe(it) }.size
    }

    fun part2(input: List<String>): Int {
        val reports = input.map { line -> line.split(" ").map { it.toInt() } }

        return reports.filter { almostSafe(it) }.size
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day02_test")
//    check(part1(testInput) == 2)
    check(part2(testInput) == 4)

    val input = readInput("Day02")
//    println(part1(input))
    println(part2(input))
}