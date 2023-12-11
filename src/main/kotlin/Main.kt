fun main() {
    val fileContent = readFileAsLinesUsingUseLines("10.txt")
    println(day10_1(fileContent))
    println(day10_2(fileContent))
}

fun readFileAsLinesUsingUseLines(fileName: String): List<String> =
    object {}.javaClass.getResourceAsStream(fileName)?.bufferedReader()?.readLines().orEmpty()