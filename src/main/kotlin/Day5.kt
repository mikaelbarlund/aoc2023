fun day5_1(fileContent: List<String>): Any {
    val seeds = fileContent[0].substring(7).split(" ").map { i -> i.toLong() }
    val instructions = fileContent.slice(2..<fileContent.size).filter { it.isEmpty() || it[0].isDigit() }
        .fold(listOf(emptyList())) { acc: List<List<List<Long>>>, it ->
            if (it.isEmpty()) acc + listOf(emptyList()) else acc.slice(
                0..<acc.size - 1
            ) + listOf(
                acc[acc.size - 1] + listOf(
                    it.split(" ").map { i -> i.toLong() })
            )
        }
    return instructions.fold(seeds) { seedsacc, instr ->
        seedsacc.map { Pair(false, it) }
            .map { seed ->
                instr.fold(seed) { target, i ->
                    val res = if (!target.first && i[1] <= target.second && target.second < i[1] + i[2]) Pair(
                        true,
                        target.second - i[1] + i[0]
                    ) else target
                    res
                }
            }.map { it.second }

    }.min()
}


fun day5_2(fileContent: List<String>): Any {

    val seeds = fileContent[0].substring(7).split(" ").map { i -> i.toLong() }.chunked(2)
        .map { i -> LongRange(i[0], i[0] + i[1]) }
    val instructions = fileContent.slice(2..<fileContent.size).filter { it.isEmpty() || it[0].isDigit() }
        .fold(listOf(emptyList())) { acc: List<List<Triple<LongRange, LongRange, Long>>>, it ->
            if (it.isEmpty()) acc + listOf(emptyList()) else acc.slice(
                0..<acc.size - 1
            ) + listOf(
                acc[acc.size - 1] + listOf(
                    it.split(" ").map { q -> q.toLong() }
                ).map { x -> Triple((x[0]..x[0] + x[2]), (x[1]..x[1] + x[2]), x[0] - x[1]) }
            )
        }
    return instructions.fold(seeds) { seedsacc, instr ->
        seedsacc.asSequence().map { Pair(it, emptyList<LongRange>()) }
            .map { seed ->
                instr.fold(seed) { target, i ->
                    val matchingRange = longArrayOf(target.first.first, i.second.first).max() < longArrayOf(
                        target.first.last,
                        i.second.last
                    ).min()
                    val match = listOf(
                        if (matchingRange) (longArrayOf(
                            target.first.first,
                            i.second.first
                        ).max() + i.third..longArrayOf(
                            target.first.last,
                            i.second.last
                        ).min() + i.third) else (0.toLong()..0)
                    )
                    val remainder =
                        if (matchingRange)
                            if (target.first.first < i.second.first)
                                (target.first.first..<i.second.first)
                            else
                                (i.second.last..<target.first.last)
                        else
                            (0.toLong()..0)
                    Pair(
                        if (matchingRange) remainder
                        else target.first,
                        if (matchingRange) target.second + match
                        else target.second
                    )
                }
            }
            .map {
                listOf(it.first) + it.second
            }
            .reduce { a, b -> a + b }.filter { p -> p.last > p.first }.toList()
    }.minBy { it.first }.min()
}

