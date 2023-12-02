fun day1_1(fileContent: List<String>): Any {
    val digits = fileContent.map { line -> line.toCharArray().filter { it.isDigit() } }
    return digits.map { it.first().toString() + it.last() }.sumOf { it.toInt() }
}

val mapValues = listOf(
    "one" to 1,
    "1" to 1,
    "two" to 2,
    "2" to 2,
    "three" to 3,
    "3" to 3,
    "four" to 4,
    "4" to 4,
    "five" to 5,
    "5" to 5,
    "six" to 6,
    "6" to 6,
    "seven" to 7,
    "7" to 7,
    "eight" to 8,
    "8" to 8,
    "nine" to 9,
    "9" to 9
)

fun day1_2(fileContent: List<String>): Any {
    return fileContent.map { line ->
        (mapValues.minBy { if (line.indexOf(it.first) == -1) 99 else line.indexOf(it.first) }.second * 10
                + mapValues.maxBy { line.lastIndexOf(it.first) }.second)
    }.sum()
}