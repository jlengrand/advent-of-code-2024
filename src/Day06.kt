
enum class Direction {
    UP, DOWN, RIGHT, LEFT
}

fun main() {

    fun printMap(input: List<String>, obstacles : List<Pair<Int, Int>>, visited : List<Pair<Int, Int>>, position : Pair<Int, Int>, direction : Direction) {
        val depth = input.size
        val length = input[0].length

        for(i in 0 until depth -1){
            val line = input[i].toMutableList()
            for (j in 0 until length - 1){
                if (position == Pair(i, j)) {
                    if(direction == Direction.UP) { line[j] = '^' }
                    if(direction == Direction.DOWN) { line[j] = 'v' }
                    if(direction == Direction.LEFT) { line[j] = '<' }
                    if(direction == Direction.RIGHT) { line[j] = '>' }
                }
                else if (visited.contains(Pair(i, j))) {
                    line[j] = 'X'
                }
                else if (obstacles.contains(Pair(i, j))) {
                    line[j] = '#'
                }
                else {
                    line[j] = '.'
                }
            }
            println(line.joinToString(""))
        }
    }

    fun insideMap(position: Pair<Int, Int>, mapSize : Pair<Int, Int>): Boolean {
        val insideRow = position.first >= 0  && position.first < mapSize.first
        val insideCol = position.second >= 0  && position.second < mapSize.second

        return insideRow && insideCol
    }

    fun part1(input: List<String>): Int {

        val obstacles = mutableListOf<Pair<Int, Int>>()
        val visited = mutableListOf<Pair<Int, Int>>()

        var position = Pair(-1, -1)
        var direction = Direction.UP
        var mapSize = Pair(input[0].length, input.size)

        var idxCol = 0
        for (row in input){
            var idxRow = 0

            for (j in row){
                if(j == '#'){
                    obstacles.add(Pair(idxCol, idxRow))
                }

                if(j == '^'){
                    position = Pair(idxCol, idxRow)
                    direction = Direction.UP
                }
                if(j == 'v'){
                    position = Pair(idxCol, idxRow)
                    direction = Direction.DOWN
                }
                if(j == '<'){
                    position = Pair(idxCol, idxRow)
                    direction = Direction.LEFT
                }
                if(j == '>'){
                    position = Pair(idxCol, idxRow)
                    direction = Direction.RIGHT
                }
                idxRow += 1
            }

            idxCol +=1
        }

        // LOOP
        while(insideMap(position, mapSize)){

            if(direction == Direction.UP){
                visited.add(position)
                val newPosition = Pair(position.first - 1, position.second)
                if (obstacles.contains(newPosition)){
                    direction = Direction.RIGHT
                }
                else{
                    position = newPosition
                }
            }
            else if(direction == Direction.RIGHT){
                visited.add(position)
                val newPosition = Pair(position.first, position.second + 1)
                if (obstacles.contains(newPosition)){
                    direction = Direction.DOWN
                }
                else{
                    position = newPosition
                }
            }
            else if(direction == Direction.DOWN){
                visited.add(position)
                val newPosition = Pair(position.first + 1, position.second)
                if (obstacles.contains(newPosition)){
                    direction = Direction.LEFT
                }
                else{
                    position = newPosition
                }
            }
            else{ // DIRECTION LEFT
                visited.add(position)
                val newPosition = Pair(position.first, position.second - 1)
                if (obstacles.contains(newPosition)){
                    direction = Direction.UP
                }
                else{
                    position = newPosition
                }
            }
        }

        val visitedUnique = visited.toSet().toList()

        return visitedUnique.size
    }

    fun isInfinite(
        input: List<String>,
        i: Int,
        j : Int,
        baseObstacles : List<Pair<Int, Int>>,
        basePosition : Pair<Int, Int>,
        mapSize : Pair<Int, Int>,
        baseDirection : Direction
    ) : Boolean {

        println("$i $j")

        val visited = mutableListOf<Pair<Int, Int>>()
        var position = basePosition
        var direction = baseDirection
        val obstacles = baseObstacles.toMutableList()
        obstacles.add(Pair(i, j))

        val maxIteration = 100000
        var iteration = 1
        while(insideMap(position, mapSize)){
            if(iteration > maxIteration){ return true}

            if(direction == Direction.UP){
                visited.add(position)
                val newPosition = Pair(position.first - 1, position.second)
                if (obstacles.contains(newPosition)){
                    direction = Direction.RIGHT
                }
                else{
                    position = newPosition
                }
            }
            else if(direction == Direction.RIGHT){
                visited.add(position)
                val newPosition = Pair(position.first, position.second + 1)
                if (obstacles.contains(newPosition)){
                    direction = Direction.DOWN
                }
                else{
                    position = newPosition
                }
            }
            else if(direction == Direction.DOWN){
                visited.add(position)
                val newPosition = Pair(position.first + 1, position.second)
                if (obstacles.contains(newPosition)){
                    direction = Direction.LEFT
                }
                else{
                    position = newPosition
                }
            }
            else{ // DIRECTION LEFT
                visited.add(position)
                val newPosition = Pair(position.first, position.second - 1)
                if (obstacles.contains(newPosition)){
                    direction = Direction.UP
                }
                else{
                    position = newPosition
                }
            }
            iteration++

        }

        return false
    }

    fun part2(input: List<String>): Int {

        val obstacles = mutableListOf<Pair<Int, Int>>()

        var position = Pair(-1, -1)
        var direction = Direction.UP
        var mapSize = Pair(input[0].length, input.size)

        var idxCol = 0
        for (row in input){
            var idxRow = 0

            for (j in row){
                if(j == '#'){
                    obstacles.add(Pair(idxCol, idxRow))
                }

                if(j == '^'){
                    position = Pair(idxCol, idxRow)
                    direction = Direction.UP
                }
                if(j == 'v'){
                    position = Pair(idxCol, idxRow)
                    direction = Direction.DOWN
                }
                if(j == '<'){
                    position = Pair(idxCol, idxRow)
                    direction = Direction.LEFT
                }
                if(j == '>'){
                    position = Pair(idxCol, idxRow)
                    direction = Direction.RIGHT
                }
                idxRow += 1
            }

            idxCol +=1
        }

        // LOOP

        val infinites = mutableListOf<Boolean>()

        var idxCol2 = 0
        for (row in input){
            var idxRow2 = 0

            for (j in row){

                infinites.add(isInfinite(
                    input,
                    idxRow2,
                    idxCol2,
                    obstacles,
                    position,
                    mapSize,
                    direction)
                )


                idxRow2 += 1
            }

            idxCol2 +=1
        }

        val howmany = infinites.filter { it }
        return howmany.size
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day06_test")
//    check(part1(testInput) == 41)
    check(part2(testInput) == 6)

    val input = readInput("Day06")
//    println(part1(input))
    println(part2(input))
}