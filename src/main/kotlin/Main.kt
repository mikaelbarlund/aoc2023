fun main(args: Array<String>) {
    var fileContent = readFileAsLinesUsingUseLines("01_1.txt")
    if (fileContent != null) {
        println(day1_1(fileContent))
        println(day1_2(fileContent))
    }
    fileContent = readFileAsLinesUsingUseLines("02_1.txt")
    if (fileContent != null) {
        println(day2_1(fileContent))
        println(day2_2(fileContent))
    }
}

fun readFileAsLinesUsingUseLines(fileName: String): List<String>? =
    object {}.javaClass.getResourceAsStream(fileName)?.bufferedReader()?.readLines()