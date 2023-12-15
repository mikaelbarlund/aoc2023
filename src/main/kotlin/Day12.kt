fun day12_1(fileContent: List<String>): Any {
    fileContent.forEach(::println)
    println("************************")
    println()
    val x = fileContent.map { it.split(" ") }
        .map { Pair(it[0], it[1].split(",").map { y -> y.toLong() }) }
        .map {
            possibilities(it.first, it.second, 0)
        }
    return x.sum()
}

val cache = mutableMapOf<Triple<String, List<Long>, Long>, Long>()
fun possibilities(springs: String, damaged: List<Long>, i: Long): Long {
    val result = cache.getOrPut(Triple(springs, damaged, i)) {
        if (damaged.isEmpty() && i == 0L && springs.all { it == '.' }) 1
        else if (damaged.isEmpty() && (i > 0 || springs.contains('#'))) 0
        else if (springs.isEmpty() && damaged.isEmpty() && i == 0L) 1
        else if (springs.isEmpty() && damaged.size == 1 && damaged[0] == i) 1
        else if (springs.isEmpty()) 0
        else if (springs[0] == '?') possibilities(
            "." + springs.substring(1),
            damaged,
            i
        ) + possibilities("#" + springs.substring(1), damaged, i)
        else if (springs[0] == '.' && i == 0L) possibilities(springs.substring(1), damaged, 0)
        else if (springs[0] == '.' && damaged[0] == i) possibilities(
            springs.substring(1),
            damaged.subList(1, damaged.size),
            0
        )
        else if (springs[0] == '.') 0
        else possibilities(springs.substring(1), damaged, i + 1)
    }
    return result
}

fun day12_2(fileContent: List<String>): Any {
    val x = unfoldPairs(fileContent)
        .mapIndexed() { i, it ->
            possibilities(it.first, it.second, 0)
        }
    return x.sum()
}

fun unfoldPairs(fileContent: List<String>) = fileContent.map { it.split(" ") }
    .map { Pair(myRepeat(it[0], "?", 5), myRepeat(it[1], ",", 5).split(",").map { y -> y.toLong() }) }

fun myRepeat(input: String, sep: String, count: Long): String =
    (0..<count).joinToString(separator = sep) { input }
