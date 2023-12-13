fun main() {
    val fileContent = readFileAsLinesUsingUseLines("12.txt")
    println(day12_1(fileContent))
    println(day12_2(fileContent)) //1978351685
}

fun readFileAsLinesUsingUseLines(fileName: String): List<String> =
    object {}.javaClass.getResourceAsStream(fileName)?.bufferedReader()?.readLines().orEmpty()