fun main() {
    val fileContent = readFileAsLinesUsingUseLines("07.txt")
    println(day7_1(fileContent))
    println(day7_2(fileContent))
}

fun readFileAsLinesUsingUseLines(fileName: String): List<String> =
    object {}.javaClass.getResourceAsStream(fileName)?.bufferedReader()?.readLines().orEmpty()