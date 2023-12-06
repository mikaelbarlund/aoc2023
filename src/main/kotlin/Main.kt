fun main() {
    var fileContent = readFileAsLinesUsingUseLines("01_1.txt")
    if (false) {
        println(day1_1(fileContent))
        println(day1_2(fileContent))

        fileContent = readFileAsLinesUsingUseLines("02_1.txt")
        println(day2_1(fileContent))
        println(day2_2(fileContent))

        fileContent = readFileAsLinesUsingUseLines("03_1.txt")
        println(day3_1(fileContent))
        println(day3_2(fileContent))

        fileContent = readFileAsLinesUsingUseLines("04_1.txt")
        println(day4_1(fileContent))
        println(day4_2(fileContent))
    }
    fileContent = readFileAsLinesUsingUseLines("05_1.txt")
    println(day5_1(fileContent))
    println(day5_2(fileContent))
}

fun readFileAsLinesUsingUseLines(fileName: String): List<String> =
    object {}.javaClass.getResourceAsStream(fileName)?.bufferedReader()?.readLines().orEmpty()