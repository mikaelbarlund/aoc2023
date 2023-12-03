fun day3_1firstGo(fileContent: List<String>): Any {
    val input = fileContent.map { line -> (".$line.").toCharArray().map { it.toString() } }
    val emptyLine = listOf((1..input[0].size).map { "." })
    val grid = emptyLine + input + emptyLine

    val x = grid.foldIndexed(0) { x, acc, yes ->
        acc + yes.foldIndexed(Triple(0, "", false)) { y, lineacc, cell ->
            var foo = lineacc.first
            var tmp = lineacc.second
            var isPart = lineacc.third
            if (cell.toDoubleOrNull() != null) {
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
                if (cell.toDoubleOrNull() == null && lineacc.second != "" && lineacc.third) lineacc.first + lineacc.second.toInt() else lineacc.first,
                if (cell.toDoubleOrNull() != null) (if (lineacc.second == "") cell else lineacc.second + cell) else "",
                (cell.toDoubleOrNull() != null) && (lineacc.third || adjacentSymbol(y, x, grid))
            )
        }.first
    }
    return x
}

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

fun day3_2(fileContent: List<String>): Any {
    return 1
}