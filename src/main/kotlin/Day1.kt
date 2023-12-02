fun day1_1(fileContent: List<String>): Any {
    val digits = fileContent.map { line -> line.toCharArray().filter { it.isDigit() } }
    return digits.map { it.first().toString() + it.last() }.sumOf { it.toInt() }
}

val mapValues = listOf(
    Pair("one", 1),
    Pair("1", 1),
    Pair("two", 2),
    Pair("2", 2),
    Pair("three", 3),
    Pair("3", 3),
    Pair("four", 4),
    Pair("4", 4),
    Pair("five", 5),
    Pair("5", 5),
    Pair("six", 6),
    Pair("6", 6),
    Pair("seven", 7),
    Pair("7", 7),
    Pair("eight", 8),
    Pair("8", 8),
    Pair("nine", 9),
    Pair("9", 9)
)

fun day1_2(fileContent: List<String>): Any {
    return fileContent.map { line ->
        (mapValues.minBy { if (line.indexOf(it.first) == -1) 99 else line.indexOf(it.first) }.second * 10
                + mapValues.maxBy { line.lastIndexOf(it.first) }.second)
    }.sum()
}