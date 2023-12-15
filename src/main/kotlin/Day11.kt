import kotlin.math.abs

fun day11_1(fileContent: List<String>): Any {
    val expandY = fileContent
        .map { it.toList() }
        .fold(emptyList()) { acc: List<List<Char>>, it ->
            acc + if (it.any { c -> c != '.' }) listOf(it) else listOf(it, it)
        }
    val expandXY = expandY.transpose().fold(emptyList()) { acc: List<List<Char>>, it ->
        acc + if (it.any { c -> c != '.' }) listOf(it) else listOf(it, it)
    }.transpose()
    val galaxies = expandXY.foldIndexed(emptyList()) { x, a1: List<Pair<Int, Int>>, i1 ->
        a1 + i1.foldIndexed(emptyList()) { y, a2: List<Pair<Int, Int>>, i2 ->
            if (i2 == '#') a2 + listOf(Pair(x, y)) else a2

        }
    }
    val pairs = galaxies.fold(
        Pair(
            emptyList(),
            emptyList()
        )
    ) { acc: Pair<List<Pair<Int, Int>>, List<Pair<Pair<Int, Int>, Pair<Int, Int>>>>, it1 ->
        Pair(acc.first + listOf(it1),
            acc.second + acc.first.map { it2 -> Pair(it1, it2) }
        )
    }.second
    val distances = pairs.map { abs(it.first.first - it.second.first) + abs(it.first.second - it.second.second) }
    return distances.sum()
}

fun day11_2(fileContent: List<String>, factor: Long): Any {
    val expandY = fileContent
        .map { it.toList() }
        .fold(emptyList()) { acc: List<List<Char>>, it ->
            acc + if (it.any { c -> c != '.' }) listOf(it) else listOf(it.map { '@' })
        }
    val expandXY = expandY.transpose().fold(emptyList()) { acc: List<List<Char>>, it ->
        acc + if (it.any { c -> c != '.' && c != '@' }) listOf(it) else listOf(it.map { '@' })
    }.transpose()
    val galaxies = expandXY.foldIndexed(emptyList()) { x, a1: List<Pair<Int, Int>>, i1 ->
        a1 + i1.foldIndexed(emptyList()) { y, a2: List<Pair<Int, Int>>, i2 ->
            if (i2 == '#') a2 + listOf(Pair(x, y)) else a2

        }
    }
    val pairs = galaxies.fold(
        Pair(
            emptyList(),
            emptyList()
        )
    ) { acc: Pair<List<Pair<Int, Int>>, List<Pair<Pair<Int, Int>, Pair<Int, Int>>>>, it1 ->
        Pair(acc.first + listOf(it1),
            acc.second + acc.first.map { it2 -> Pair(it2, it1) }
        )
    }.second
    val distances = pairs.map {
        evalDistance(it, expandXY, factor)
    }
    return distances.sum()
}

private fun evalDistance(it: Pair<Pair<Int, Int>, Pair<Int, Int>>, map: List<List<Char>>, factor: Long): Long {
    val xRange =
        if (it.first.first < it.second.first) (it.first.first).rangeTo(it.second.first) else (it.first.first).downTo(
            it.second.first
        )
    val yRange =
        if (it.first.second < it.second.second) (it.first.second).rangeTo(it.second.second) else (it.first.second).downTo(
            it.second.second
        )
    val horizontalDistance = xRange.foldIndexed(0L) { i, a, x ->
        if (i == 0) a
        else {
            a + if (map[x][yRange.first] == '@') factor else 1L
        }
    }
    val totalDistance = yRange.foldIndexed(horizontalDistance) { i, a, y ->
        if (i == 0) a
        else {
            a + if (map[xRange.last][y] == '@') factor else 1L
        }
    }
    return totalDistance
}

