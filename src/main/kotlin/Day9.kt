fun day9_1(fileContent: List<String>): Any {
    val x = fileContent.map { it.split(" ").map { it.toLong() } }
        .map { addSequence(listOf(it)) }
        .map { a -> a.sumOf { b -> b.last() } }.sum()
    return x
}

tailrec fun addSequence(acc: List<List<Long>>): List<List<Long>> {
    return if (!acc.last().any { it != 0L }) acc
    else addSequence(
        acc + listOf(getNextSequence(acc.last(), emptyList()))
    )
}

tailrec fun getNextSequence(sequence: List<Long>, nextSequence: List<Long>): List<Long> {
    return if (sequence.size == 1) nextSequence
    else getNextSequence(
        sequence.subList(1, sequence.size),
        nextSequence + listOf(sequence[1] - sequence[0])
    )
}

fun day9_2(fileContent: List<String>): Any {
    return 1
}
