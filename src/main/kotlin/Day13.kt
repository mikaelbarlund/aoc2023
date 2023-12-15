fun day13_1(fileContent: List<String>): Any {

    val x = fileContent.fold(listOf(emptyList<List<Char>>())) { acc, it ->
        if (it.isEmpty()) acc + listOf(emptyList())
        else acc.subList(0, acc.size - 1) + listOf(acc.last() + listOf(it.toList()))
    }
    val y = x.map { findReflectionMemo(it) }
    return y.sumOf { it.first }
}

val cache13 = mutableMapOf<List<Any>, Pair<Int, Boolean>>()
tailrec fun findReflectionMemo(
    field: List<List<Char>>,
    loopIndexX: Int = 0,
    loopIndexY: Int = 1,
    index: Int = 0,
    transposed: Boolean = false,
    original: Pair<Int, Boolean> = Pair(-1, false)
): Pair<Int, Boolean> {
    val cached = cache13.getOrPut(listOf(field, loopIndexX, loopIndexY, index, transposed)) {
        val result = Pair((index + 1) * if (transposed) 1 else 100, transposed)
        if (loopIndexY > field.size || (index < field.size - 1 && (loopIndexX < 0 || loopIndexY == field.size)) && result != original) {
            return result
        } else if ((index == field.size || loopIndexY == field.size || loopIndexX < 0) && result != original) {
            return if (transposed) {
                Pair(-1, transposed)
            } else {
                val transposedField = transpose(field)
                return findReflectionMemo(
                    field = transposedField,
                    transposed = true,
                    original = original
                )
            }
        } else if (result != original && index < field.size - 1 && field[loopIndexX] == field[loopIndexY]) {
            return findReflectionMemo(
                field,
                loopIndexX - 1,
                loopIndexY + 1,
                index,
                transposed,
                original
            )
        } else {
            return findReflectionMemo(
                field,
                index + 1,
                index + 2,
                index + 1,
                transposed,
                original
            )
        }
    }
    return cached

}


fun day13_2(fileContent: List<String>): Any {
    val x = fileContent.fold(listOf(emptyList<List<Char>>())) { acc, it ->
        if (it.isEmpty()) acc + listOf(emptyList())
        else acc.subList(0, acc.size - 1) + listOf(acc.last() + listOf(it.toList()))
    }.map { Pair(it, findReflectionMemo(it, 0, 1, 0, false)) }
    val y = x.mapIndexed { i, it ->
        otherReflection(it.first, it.second, 0)
    }
    return y.sumOf { it.first }
}

val cache132 = mutableMapOf<List<Any>, Pair<Int, Boolean>>()


tailrec fun otherReflection(field: List<List<Char>>, original: Pair<Int, Boolean>, index: Int): Pair<Int, Boolean> {
    val result = cache132.getOrPut(listOf(field, original, index)) {
        val x = index % field.size
        val y = (index - x) / field.size
        if (y > field[0].size) throw Exception("wtf")
        val newField = field.mapIndexed { xi, row ->
            row.mapIndexed { yi, char ->
                if (x == xi && y == yi) other(char) else char
            }
        }
        val otherReflection = findReflectionMemo(newField, original = original)
        return if (otherReflection.first > 0 && otherReflection != original) otherReflection else otherReflection(
            field,
            original,
            index + 1
        )
    }
    return result
}

fun other(input: Char): Char {
    return if (input == '.') '#' else '.'
}