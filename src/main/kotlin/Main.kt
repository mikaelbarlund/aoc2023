fun main() {
    val fileContent = readFileAsLinesUsingUseLines("09.txt")
    println(day9_1(fileContent))
    println(day9_2(fileContent))
}

fun readFileAsLinesUsingUseLines(fileName: String): List<String> =
    object {}.javaClass.getResourceAsStream(fileName)?.bufferedReader()?.readLines().orEmpty()