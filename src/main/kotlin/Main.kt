fun main() {
    val fileContent = readFileAsLinesUsingUseLines("08.txt")
    println(day8_1(fileContent))
    println(day8_2(fileContent))
}

fun readFileAsLinesUsingUseLines(fileName: String): List<String> =
    object {}.javaClass.getResourceAsStream(fileName)?.bufferedReader()?.readLines().orEmpty()