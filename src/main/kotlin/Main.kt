fun main() {
    val fileContent = readFileAsLinesUsingUseLines("11.txt")
    println(day11_1(fileContent))
    println(day11_2(fileContent, 1000000L))
}

fun readFileAsLinesUsingUseLines(fileName: String): List<String> =
    object {}.javaClass.getResourceAsStream(fileName)?.bufferedReader()?.readLines().orEmpty()