fun List<String>.toCharMatrix(): List<List<Char>> {
    return this.map { it.toList() }
}

fun List<String>.toLongMatrix(): List<List<Long>> {
    return this.map { it.toList().map { long -> long.code.toLong() } }
}

fun <T> List<List<T>>.transpose(): List<List<T>> {
    val cols = this[0].size
    val rows = this.size
    return List(cols) { j ->
        List(rows) { i ->
            this[i][j]
        }
    }
}