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
    val rows = fileContent.map { it.substring(8).split("|") }
        .map { Pair(it[0].trim().split("\\s+".toRegex()), it[1].trim().split("\\s+".toRegex())) }
        .foldIndexed(Pair(0, fileContent.indices.map { 1 })) { i, acc, it ->
            val matches = it.first.fold(0) { card, num ->
                if (it.second.contains(num)) card + 1 else card
            }
            Pair(acc.first + acc.second[0], newxtCards(matches, acc.second))
        }
    return rows.first
}

fun newxtCards(matches: Int, cards: List<Int>): List<Int> {
    return cards.slice(1..cards.size - 1).mapIndexed { i, it ->
        if (i < matches) it + cards[0] else it
    }
}
