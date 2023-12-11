val pipeMapping = listOf(
    Triple(listOf('S', '|', 'J', 'L'), Pair(-1, 0), listOf('F', '7', '|', 'S')),
    Triple(listOf('S', '-', 'L', 'F'), Pair(0, 1), listOf('7', 'J', '-', 'S')),
    Triple(listOf('S', '|', '7', 'F'), Pair(1, 0), listOf('J', 'L', '|', 'S')),
    Triple(listOf('S', '-', '7', 'J'), Pair(0, -1), listOf('F', 'L', '-', 'S')),
)

fun day10_1(fileContent: List<String>): Any {
    val loop = getPipeLoop(fileContent)
    return loop.size / 2
}

private fun getPipeLoop(fileContent: List<String>): List<Pair<Int, Int>> {
    val wrapper = ".".repeat(fileContent[0].length)
    val map = (listOf(wrapper) +
            fileContent
            + listOf(wrapper))
        .map { (".$it.").toCharArray() }
    val start = map.foldIndexed(Pair(0, 0)) { x, xa, xit ->
        xit.foldIndexed(xa) { y, ya, yit ->
            if (yit == 'S') Pair(x, y) else ya
        }
    }
    val loop = move(map, start, start, start, emptyList())
    return loop
}

tailrec fun move(
    map: List<CharArray>,
    start: Pair<Int, Int>,
    previous: Pair<Int, Int>,
    current: Pair<Int, Int>,
    distance: List<Pair<Int, Int>>
): List<Pair<Int, Int>> {
    return if (start == current && distance.size > 1) distance else
        move(map, start, current, nextStop(previous, current, map), distance + listOf(current))
}


fun nextStop(previous: Pair<Int, Int>, current: Pair<Int, Int>, map: List<CharArray>): Pair<Int, Int> {
    val rules = pipeMapping.filter { it.first.contains(map[current.first][current.second]) }
    return rules.fold(Pair(0, 0)) { acc, it ->
        if (it.third.contains(map[current.first + it.second.first][current.second + it.second.second])
            && previous != Pair(current.first + it.second.first, current.second + it.second.second)
        )
            Pair(current.first + it.second.first, current.second + it.second.second) else acc
    }

}

fun day10_2(fileContent: List<String>): Any {
    val x = fileContent[0].length + 2
    val y = fileContent.size + 2
    val wrapper = ".".repeat(fileContent[0].length)
    val map = (listOf(wrapper) +
            fileContent
            + listOf(wrapper))
        .map { (".$it.").toCharArray() }
    val start = map.foldIndexed(Pair(0, 0)) { x, xa, xit ->
        xit.foldIndexed(xa) { y, ya, yit ->
            if (yit == 'S') Pair(x, y) else ya
        }
    }
    val loop = move(map, start, start, start, emptyList())
    val inside = (0..<x).fold(emptyList<Pair<Int, Int>>()) { a1, i1 ->
        a1 + (0..<y).fold(emptyList()) { a2, i2 ->
            if (
                inside(map, Pair(i1, i2), loop, x, y)
            ) a2 + listOf(Pair(i1, i2)) else a2
        }
    }
    return inside.size
}

fun inside(map: List<CharArray>, point: Pair<Int, Int>, loop: List<Pair<Int, Int>>, x: Int, y: Int): Boolean {
    if (loop.contains(Pair(point.second, point.first))) return false
    val crosses = (point.first..<x).fold(0) { a, it ->
        val cross = (loop.contains(Pair(point.second, it)) && listOf('|', 'J', 'L').contains(map[point.second][it]))
        if (cross) a + 1 else a
    }
    return crosses % 2 == 1
}

