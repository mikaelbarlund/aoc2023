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

fun <T> List<T>.customBubbleSort(compare: (a: T, b: T) -> Boolean): List<T> {
    val arr = this.toMutableList()
    val n = arr.size
    for (i in 0..<n - 1) {
        for (j in 0..<n - i - 1) {
            if (compare(arr[j], arr[j + 1])) {
                val temp = arr[j]
                arr[j] = arr[j + 1]
                arr[j + 1] = temp
            }
        }
    }
    return arr.toList()
}