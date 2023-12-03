fun day3_1(fileContent: List<String>): Any {
    val input = fileContent.map { line -> (".$line.").toCharArray().map { it.toString() } }
    val emptyLine = listOf((1..input[0].size).map { "." })
    val grid = emptyLine + input + emptyLine
    var tmp = ""
    var isPart = false
    val x = grid.foldIndexed(0) { x, acc, yes ->
        acc + yes.foldIndexed(0) { y, lineacc, cell ->
            var foo = lineacc
            if (cell.toDoubleOrNull() != null) {
                isPart = isPart || adjeacentSymbol(y, x, grid)
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
            foo
        }
    }
    return x
}

private fun adjeacentSymbol(
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