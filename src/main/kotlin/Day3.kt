fun day3_1firstGo(fileContent: List<String>): Any {
    val input = fileContent.map { line -> (".$line.").toCharArray().map { it.toString() } }
    val emptyLine = listOf((1..input[0].size).map { "." })
    val grid = emptyLine + input + emptyLine

    val x = grid.foldIndexed(0) { x, acc, yes ->
        acc + yes.foldIndexed(Triple(0, "", false)) { y, lineacc, cell ->
            var foo = lineacc.first
            var tmp = lineacc.second
            var isPart = lineacc.third
            if (isDigit(cell)) {
                isPart = isPart || adjacentSymbol(y, x, grid)
                tmp = if (tmp == "")
                    cell
                else
                    tmp + cell
            } else
                if (tmp != "") {
                    arrayOf(tmp)
                    if (isPart)
                        foo += tmp.toInt()
                    isPart = false
                    tmp = ""
                }
            Triple(foo, tmp, isPart)
        }.first
    }
    return x
}

fun day3_1(fileContent: List<String>): Any {
    val input = fileContent.map { line -> (".$line.").toCharArray().map { it.toString() } }
    val emptyLine = listOf((1..input[0].size).map { "." })
    val grid = emptyLine + input + emptyLine
    val x = grid.foldIndexed(0) { x, acc, line ->
        acc + line.foldIndexed(Triple(0, "", false)) { y, lineacc, cell ->
            Triple(
                if (!isDigit(cell) && lineacc.second != "" && lineacc.third) lineacc.first + lineacc.second.toInt() else lineacc.first,
                if (isDigit(cell)) (if (lineacc.second == "") cell else lineacc.second + cell) else "",
                isDigit(cell) && (lineacc.third || adjacentSymbol(y, x, grid))
            )
        }.first
    }
    return x
}

private fun isDigit(cell: String) = cell.toDoubleOrNull() != null

private fun adjacentSymbol(
    y: Int,
    x: Int,
    grid: List<List<String>>
) = (y - 1..y + 1).fold(false) { a1, y1 ->
    a1 || (x - 1..x + 1).fold(false) { a2, x1 ->
        a2 || isSymbol(grid[x1][y1])
    }
}

fun isSymbol(s: String): Boolean {
    return s != "." && s.toDoubleOrNull() == null
}

fun isGear(s: String): Boolean {
    return s == "*"
}

fun day3_2(fileContent: List<String>): Any {
    val input = fileContent.map { line -> (".$line.").toCharArray().map { it.toString() } }
    val emptyLine = listOf((1..input[0].size).map { "." })
    val grid = emptyLine + input + emptyLine

    val parts = (0..grid.size - 1).fold(emptyArray()) { acc: Array<Pair<Int, List<Pair<Int, Int>>>>, x ->
        acc + (0..grid[0].size - 1).fold(emptyArray()) { acc1: Array<Pair<Int, List<Pair<Int, Int>>>>, y ->
            if (isDigit(grid[x][y]) && !isDigit(grid[x][y - 1])) acc1 + arrayOf(toPair(getNum(grid, x, y), x, y)) else
                acc1
        }
    }
    val gears = grid.foldIndexed(emptyList()) { x, acc: List<Pair<Int, Int>>, line ->
        acc + line.foldIndexed(emptyList()) { y, acc1: List<Pair<Int, Int>>, cell ->
            if (isGear(cell)) acc1 + listOf(Pair(x, y)) else acc1
        }
    }
    val x = gears.fold(0) { acc, it ->
        acc + findAdjacents(it, parts)
    }
    return x
}

fun findAdjacents(gear: Pair<Int, Int>, parts: Array<Pair<Int, List<Pair<Int, Int>>>>): Int {
    val adjacents = parts.filter {
        it.second.fold(false) { isNear, partLoc ->
            isNear || (
                    gear.first <= partLoc.first + 1 &&
                            gear.first >= partLoc.first - 1 &&
                            gear.second <= partLoc.second + 1 &&
                            gear.second >= partLoc.second - 1
                    )
        }
    }.map { it.first }

    return if (adjacents.size == 2) adjacents.reduce { a, b -> a * b } else 0
}

fun toPair(num: String, x: Int, y: Int): Pair<Int, List<Pair<Int, Int>>> {
    return Pair(num.toInt(), (0..num.length - 1).map {
        Pair(x, y + it)
    })
}

fun getNum(grid: List<List<String>>, x: Int, y: Int): String {
    return grid[x].slice(y..grid[x].size - 1).takeWhile { isDigit(it) }.reduce { acc, it -> acc + it }
}
