import kotlin.time.measureTime

fun main() {
    val fileContent = readFileAsLinesUsingUseLines("16.txt")
    val timeTaken1 = measureTime {
        println(day16_1(fileContent))
    }
    println(timeTaken1)
    val timeTaken2 = measureTime {
        println(day16_2(fileContent))
    }
    println(timeTaken2)
}

fun readFileAsLinesUsingUseLines(fileName: String): List<String> =
    object {}.javaClass.getResourceAsStream(fileName)?.bufferedReader()?.readLines().orEmpty()