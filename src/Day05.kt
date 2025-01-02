fun main() {

    fun isPairValid(pair: Pair<Int, Int>, update : List<Int>) : Boolean{
        return update.indexOf(pair.second) > update.indexOf(pair.first)
    }

    fun isValid(pairs: List<Pair<Int, Int>>, update: List<Int>) : Boolean {
        val relevantPairs = pairs.filter { pair -> update.contains(pair.second) && update.contains(pair.first) }
        return relevantPairs.all { isPairValid(it, update) }
    }

    fun reorder(pairs: List<Pair<Int, Int>>, update: List<Int>) : List<Int> {
        val relevantPairs = pairs.filter { pair -> update.contains(pair.second) && update.contains(pair.first) }
        val movableUpdate = update.toMutableList()

        while(!isValid(pairs, movableUpdate)){

            for(pair in relevantPairs){

                if(!isPairValid(pair, movableUpdate)){
                    val indexValue = movableUpdate.indexOf(pair.first)
                    val indexSecond = movableUpdate.indexOf(pair.second)

                    movableUpdate.removeAt(indexValue)
                    movableUpdate.add(indexSecond, pair.first)
                }
            }
        }

        return movableUpdate
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

        return updates
            .filter { isValid(pairs, it) }
            .sumOf { update -> update[update.size / 2] }
    }

    fun part2(input: List<String>): Int {
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

        return updates
            .filter { !isValid(pairs, it) } // incorrect
            .map { reorder(pairs, it) } // reordered
            .sumOf { update -> update[update.size / 2] }
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day05_test")
    check(part1(testInput) == 143)
    check(part2(testInput) == 123)

    val input = readInput("Day05")
    println(part1(input))
    println(part2(input))
}