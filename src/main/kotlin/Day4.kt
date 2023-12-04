import kotlin.math.pow

fun day4_1(fileContent: List<String>): Any {
    val rows = fileContent.map { it.substring(8).split("|") }
        .map { Pair(it[0].trim().split("\\s+".toRegex()), it[1].trim().split("\\s+".toRegex())) }
        .map {
            it.first.fold(-1) { acc, num ->
                if (it.second.contains(num)) acc + 1 else acc
            }
        }.map { if (it >= 0) 2.0.pow(it) else 0.0 }
        .sum()
    return rows
}

fun day4_2(fileContent: List<String>): Any {
    val input = fileContent.map { line -> (".$line.").toCharArray().map { it.toString() } }

    return 1
}
