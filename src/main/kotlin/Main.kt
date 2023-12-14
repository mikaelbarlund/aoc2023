fun main() {
    val fileContent = readFileAsLinesUsingUseLines("13.txt")
    println(day13_1(fileContent))
    println(day13_2(fileContent)) //1978351685
}

fun readFileAsLinesUsingUseLines(fileName: String): List<String> =
    object {}.javaClass.getResourceAsStream(fileName)?.bufferedReader()?.readLines().orEmpty()