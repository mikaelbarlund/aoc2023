fun day14_1(fileContent: List<String>): Any {
    val x = fileContent.toCharMatrix()
        .tiltAndSpin()
    return x.map { it.mapIndexed { i1, it1 -> if (it1 == 'O') i1 + 1 else 0 } }.sumOf { it.sum() }
}

val cache14_2 = mutableMapOf<List<List<Char>>, List<List<Char>>>()
fun List<List<Char>>.tiltAndSpin(): List<List<Char>> {
    val result = cache14_2.getOrPut(this) {
        this
            .transpose()
            .map {
                it.day14bubbleSort().reversed()
            }
    }
    return result
}

val cache14_3 = mutableMapOf<Int, List<List<Char>>>()
fun List<List<Char>>.fullCycle(): List<List<Char>> {
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

tailrec fun doCycles(
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

val cache14_1 = mutableMapOf<List<Char>, List<Char>>()

fun List<Char>.day14bubbleSort(): List<Char> {
    val result = cache14_1.getOrPut(this) {
        val arr = this.toMutableList()
        val n = arr.size
        for (i in 0..<n - 1) {
            for (j in 0..<n - i - 1) {
                //if (arr[j] > arr[j + 1]) {
                if (lessThan(arr[j], arr[j + 1])) {
                    // Swap the elements
                    val temp = arr[j]
                    arr[j] = arr[j + 1]
                    arr[j + 1] = temp
                }
            }
        }
        return arr.toList()
    }
    return result
}

private fun lessThan(a: Char, b: Char): Boolean {
    return a == '.' && b == 'O'
}