fun main() {

    fun isValid(pairs: List<Pair<Int, Int>>, update: List<Int>) : Boolean {

        for(v in update){
            val relevantPairs = pairs.filter { pair -> pair.first == v }
            for (pair in relevantPairs) {
                val after = pair.second

                val indexV = update.indexOf(v)
                val indexS = update.indexOf(after)

                if(indexS != -1 && indexV > indexS) return false

            }
        }

        return true
    }

    fun part1(input: List<String>): Int {

        val pairs = mutableListOf<Pair<Int, Int>>()
        val updates = mutableListOf<List<Int>>()

        for (line in input) {
            if(line.contains("|")){
                val pages = line.split("|")
                pairs.add(Pair(pages[0].toInt(), pages[1].toInt()))
            }
            else if(line.contains(",")){
                updates.add(line.split(",").map { it.toInt() })
            }
        }

        val midItems = updates
            .filter { isValid(pairs, it) }
            .map{ update -> update[update.size / 2] }


        return midItems.sum()
    }

    fun part2(input: List<String>): Int {
        TODO()
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day05_test")
    check(part1(testInput) == 143)
//    check(part2(testInput) == 5)

    val input = readInput("Day05")
    println(part1(input))
    println(part2(input))
}