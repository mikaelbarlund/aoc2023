import kotlin.time.measureTime

fun main() {
    val fileContent = readFileAsLinesUsingUseLines("19.txt")
    val timeTaken1 = measureTime {
        println(day19_1(fileContent))
    }
    println(timeTaken1)
    val timeTaken2 = measureTime {
        println(day19_2(fileContent))
    }
    println(timeTaken2)
}

fun readFileAsLinesUsingUseLines(fileName: String): List<String> =
    object {}.javaClass.getResourceAsStream(fileName)?.bufferedReader()?.readLines().orEmpty()