import kotlin.time.measureTimedValue

data class Coordinate(val x: Int, val y: Int)

fun day16_1(fileContent: List<String>): Any {

    val map = fileContent.toCharMatrix()
    val current = Coordinate(0, 0)
    val energized = step(map, Pair(current, null), emptyList(), listOf(current))
    return energized.size
}

fun Int.outside(a: Int, b: Int): Boolean {
    return this < a || this > b
}

val preventLoop = mutableListOf<Triple<Coordinate, Coordinate, Int>>()
val cache16 = mutableMapOf<Pair<Pair<Coordinate, Coordinate?>, List<Pair<Coordinate, Coordinate>>>, List<Coordinate>>()
tailrec fun step(
    map: List<List<Char>>,
    path: Pair<Coordinate, Coordinate?>,
    todo: List<Pair<Coordinate, Coordinate>>,
    coords: List<Coordinate>,
    entry: Coordinate = Coordinate(-1, 0)
): List<Coordinate> {
    val result = cache16.getOrPut(Pair(path, todo)) {
        val current = path.first
        val previous: Coordinate = if (path.second != null) path.second!! else entry
        val direction = direction(previous, current)
        val what = map[current.y][current.x]
        val next = getNextCoordinates(what, direction, current, previous).filter { !isOutside(it, map) }

        if (next.isEmpty() && todo.isEmpty()) {
            return coords
        } else {
            val nextTodo =
                if (next.isEmpty())
                    todo.subList(1, todo.size)
                else if (next.size == 1) {
                    todo
                } else
                    todo + listOf(
                        Pair(next[1], current)
                    )

            val nextPair =
                if (next.isEmpty())
                    Pair(todo[0].first, todo[0].second)
                else
                    Pair(next[0], path.first)
            val nextCoords = (coords + next).distinct()
            return step(
                map,
                nextPair,
                nextTodo,
                nextCoords
            )
        }
    }
    return result
}

private fun isOutside(current: Coordinate, map: List<List<Char>>) =
    (current.x.outside(0, map[0].size - 1) ||
            current.y.outside(0, map.size - 1)
            )

val cache16_1 = mutableMapOf<List<Any>, List<Coordinate>>()
private fun getNextCoordinates(

    what: Char,
    direction: Int,
    current: Coordinate,
    previous: Coordinate
): List<Coordinate> {
    val result = cache16_1.getOrPut(listOf(what, direction, current, previous)) {
        if (preventLoop.contains(Triple(current, previous, direction)))
            return emptyList()
        preventLoop.add(Triple(current, previous, direction))
        val next = when (what) {
            '-' -> when (direction) {
                1, 3 -> listOf(
                    Coordinate(current.x - 1, current.y),
                    Coordinate(current.x + 1, current.y)
                )

                2 -> listOf(Coordinate(current.x + 1, current.y))
                else -> listOf(Coordinate(current.x - 1, current.y))
            }

            '|' -> when (direction) {
                1 -> listOf(Coordinate(current.x, current.y - 1))
                2, 4 -> listOf(
                    Coordinate(current.x, current.y - 1),
                    Coordinate(current.x, current.y + 1)
                )

                else -> listOf(Coordinate(current.x, current.y + 1))
            }

            '/' -> when (direction) {
                1 -> listOf(Coordinate(current.x + 1, current.y))
                2 -> listOf(Coordinate(current.x, current.y - 1))
                3 -> listOf(Coordinate(current.x - 1, current.y))
                else -> listOf(Coordinate(current.x, current.y + 1))
            }

            '\\' -> when (direction) {
                1 -> listOf(Coordinate(current.x - 1, current.y))
                2 -> listOf(Coordinate(current.x, current.y + 1))
                3 -> listOf(Coordinate(current.x + 1, current.y))
                else -> listOf(Coordinate(current.x, current.y - 1))
            }

            else -> when (direction) {
                1 -> listOf(Coordinate(current.x, current.y - 1))
                2 -> listOf(Coordinate(current.x + 1, current.y))
                3 -> listOf(Coordinate(current.x, current.y + 1))
                else -> listOf(Coordinate(current.x - 1, current.y))
            }
        }

        return next
    }
    return result
}


fun direction(previous: Coordinate, current: Coordinate): Int {
    return when {
        previous.y < current.y -> 3
        previous.x < current.x -> 2
        previous.y > current.y -> 1
        else -> 4
    }
}

fun day16_2(fileContent: List<String>): Any {
    val map = fileContent.toCharMatrix()
    val width = map[0].size
    val height = map.size
    val currents = (0..<height).map { Coordinate(0, it) } + (0..<height).map { Coordinate(width - 1, it) } +
            (0..<width).map { Coordinate(it, 0) } + (0..<width).map { Coordinate(it, width - 1) }
    val entries = (0..<height).map { Coordinate(-1, it) } + (0..<height).map { Coordinate(width, it) } +
            (0..<width).map { Coordinate(it, -1) } + (0..<width).map { Coordinate(it, width) }
    val foo = currents.mapIndexed() { i, current ->
        preventLoop.clear()
        val (energized, timeTaken) = measureTimedValue {
            step(map, Pair(current, null), emptyList(), listOf(current), entries[i])
        }
        println(timeTaken)
        energized.size
    }
    return foo.max()
}