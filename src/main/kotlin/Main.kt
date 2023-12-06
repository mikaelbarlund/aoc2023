fun main() {
    val fileContent = readFileAsLinesUsingUseLines("06.txt")
    println(day6_1(fileContent))
    println(day6_2(fileContent))
}

fun readFileAsLinesUsingUseLines(fileName: String): List<String> =
    object {}.javaClass.getResourceAsStream(fileName)?.bufferedReader()?.readLines().orEmpty()