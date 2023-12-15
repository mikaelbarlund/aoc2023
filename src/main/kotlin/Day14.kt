fun day14_1(fileContent: List<String>): Any {
    val x = fileContent.toCharMatrix()
        .tiltAndSpin()
    return x.map { it.mapIndexed { i1, it1 -> if (it1 == 'O') i1 + 1 else 0 } }.sumOf { it.sum() }
}

private val cache14_2 = mutableMapOf<List<List<Char>>, List<List<Char>>>()
private fun List<List<Char>>.tiltAndSpin(): List<List<Char>> {
    val result = cache14_2.getOrPut(this) {
        this
            .transpose()
            .map {
                it.customBubbleSort { a, b -> a == '.' && b == 'O' }.reversed()
            }
    }
    return result
}

private val cache14_3 = mutableMapOf<Int, List<List<Char>>>()
private fun List<List<Char>>.fullCycle(): List<List<Char>> {
    val result = cache14_3.getOrPut(this.hashCode()) {
        (0..<4).fold(this) { a, _ -> a.tiltAndSpin() }
    }
    return result
}

fun day14_2(fileContent: List<String>): Any {
    val n = 1000000000L
    val x = fileContent.toCharMatrix()
    val y = doCycles(n, x)
        .transpose()
        .map { it.reversed() }
    return y.map { it.mapIndexed { i1, it1 -> if (it1 == 'O') i1 + 1 else 0 } }.sumOf { it.sum() }
}

data class FirstHash(val found: Boolean, val hash: Int, val index: Int)

private tailrec fun doCycles(
    n: Long,
    x: List<List<Char>>,
    firstHash: FirstHash = FirstHash(false, 0, 0),
    afterHash: List<List<List<Char>>> = emptyList()
): List<List<Char>> {
    val hash = x.hashCode()
    return if (cache14_3.keys.contains(x.hashCode()) && !firstHash.found) {
        doCycles(n, x.fullCycle(), FirstHash(true, hash, firstHash.index), afterHash + listOf(x))
    } else if (firstHash.found && firstHash.hash == hash) {
        val index = ((n - firstHash.index) % afterHash.size).toInt()
        println("$n ${firstHash.index} ${afterHash.size} $index")
        afterHash[index]
    } else doCycles(
        n,
        x.fullCycle(),
        FirstHash(firstHash.found, firstHash.hash, if (firstHash.found) firstHash.index else firstHash.index + 1),
        if (firstHash.found) afterHash + listOf(x) else afterHash
    )
}