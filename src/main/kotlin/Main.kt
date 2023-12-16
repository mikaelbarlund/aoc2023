fun main() {
    val fileContent = readFileAsLinesUsingUseLines("15.txt")
    println(day15_1(fileContent))
    println(day15_2(fileContent))
}

fun readFileAsLinesUsingUseLines(fileName: String): List<String> =
    object {}.javaClass.getResourceAsStream(fileName)?.bufferedReader()?.readLines().orEmpty()