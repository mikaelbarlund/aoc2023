fun main() {
    val fileContent = readFileAsLinesUsingUseLines("14.txt")
    println(day14_1(fileContent))
    println(day14_2(fileContent))
}

fun readFileAsLinesUsingUseLines(fileName: String): List<String> =
    object {}.javaClass.getResourceAsStream(fileName)?.bufferedReader()?.readLines().orEmpty()