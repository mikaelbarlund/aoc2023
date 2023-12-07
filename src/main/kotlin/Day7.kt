val cardMap = mapOf(
    // A, K, Q, J, T, 9, 8, 7, 6, 5, 4, 3, or 2
    'A' to 'm',
    'K' to 'l',
    'Q' to 'k',
    'J' to 'j',
    'T' to 'i',
    '9' to 'h',
    '8' to 'g',
    '7' to 'f',
    '6' to 'e',
    '5' to 'd',
    '4' to 'c',
    '3' to 'b',
    '2' to 'a'
)

fun day7_1(fileContent: List<String>): Any { //250898830
    val hands = fileContent
        .map { it.split(" ") }
        .map {
            Pair(it[1].toInt(),
                it[0].toCharArray()
                    .map { i -> cardMap[i] ?: 'x' }
            )
        }
        .map { Triple(it.first, it.second.joinToString(separator = ""), handToStrength(it.second).first) }
        .sortedWith(compareBy({ it.third }, { it.second }))
    return hands.foldIndexed(0) { i, acc, it -> acc + (i + 1) * it.first }
}

fun handToStrength(hand: List<Char>): Pair<Int, String> {
    val cardGroups = hand.groupBy { it }.values.map { it.size }.sorted()
    return when {
        (cardGroups.max() == 5) -> Pair(7, "Five of a kind")
        (cardGroups.max() == 4) -> Pair(6, "Four of a kind")
        (cardGroups.max() == 3 && cardGroups.min() == 2) -> Pair(5, "Full house")
        (cardGroups.max() == 3) -> Pair(4, "Three of a kind")
        (cardGroups.count { it == 2 } == 2) -> Pair(3, "Two Pairs")
        (cardGroups.max() == 2) -> Pair(2, "One Pair")
        else -> Pair(1, "High card")
    }
}

val cardMapJoker = mapOf(
    // A, K, Q, J, T, 9, 8, 7, 6, 5, 4, 3, or 2
    'A' to 'm',
    'K' to 'l',
    'Q' to 'k',
    'J' to 'a',
    'T' to 'j',
    '9' to 'i',
    '8' to 'h',
    '7' to 'g',
    '6' to 'f',
    '5' to 'e',
    '4' to 'd',
    '3' to 'c',
    '2' to 'b'
)

fun handToStrengthJoker(hand: List<Char>): Pair<Int, String> {
    val cardGroups = hand.filter { it != 'a' }.groupBy { it }.values.map { it.size }.sorted()
    val jokers = hand.count { it == 'a' }
    return when {
        (jokers == 5 || cardGroups.max() + jokers >= 5) -> Pair(7, "Five of a kind")
        (cardGroups.max() + jokers == 4) -> Pair(6, "Four of a kind")
        (cardGroups.max() == 3 && cardGroups.min() == 2 || (jokers == 1 && cardGroups.count { it == 2 } == 2)) -> Pair(
            5,
            "Full house"
        )

        (cardGroups.max() + jokers == 3) -> Pair(4, "Three of a kind")
        (cardGroups.count { it == 2 } == 2 || (cardGroups.max() == 2 && jokers == 1)) -> Pair(3, "Two Pairs")
        (cardGroups.size == 4 || jokers == 1) -> Pair(2, "One Pair")
        else -> Pair(1, "High card")
    }
}

fun day7_2(fileContent: List<String>): Any {
    val hands = fileContent
        .map { it.split(" ") }
        .map {
            Pair(it[1].toInt(),
                it[0].toCharArray()
                    .map { i -> cardMapJoker[i] ?: 'x' }
            )
        }
        .map { Triple(it.first, it.second.joinToString(separator = ""), handToStrengthJoker(it.second).first) }
        .sortedWith(compareBy({ it.third }, { it.second }))
    return hands.foldIndexed(0) { i, acc, it -> acc + (i + 1) * it.first }
}

