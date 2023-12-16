fun day15_1(fileContent: List<String>): Any {
    return fileContent[0].split(",").map { it.hashMe() }.sum()
}

val boxes = (0..<256).map { emptyList<Pair<String, Int>>() }.toMutableList()

fun day15_2(fileContent: List<String>): Any {
    val steps = fileContent[0]
        .split(",")
        .map { it.toInstruction() }
    steps.forEach {
        if (it.second < 0) {
            boxes[it.third] = boxes[it.third].filter { i -> i.first != it.first }
        } else {
            if (!boxes[it.third].any { i -> it.first == i.first })
                boxes[it.third] = boxes[it.third] + listOf(Pair(it.first, it.second))
            else {
                boxes[it.third] = boxes[it.third].map { i -> if (it.first == i.first) Pair(it.first, it.second) else i }
            }
        }
    }
    return boxes.foldIndexed(0) { i1, a1, it1 ->
        a1 + it1.foldIndexed(0) { i2, a2, it2 ->
            a2 + (i1 + 1) * (i2 + 1) * it2.second
        }
    }
}

fun String.toInstruction(): Triple<String, Int, Int> {
    return if (this.contains('=')) {
        val instr = this.split("=")
        Triple(instr[0], instr[1].toInt(), instr[0].hashMe())
    } else {
        val instr = this.split("-")
        Triple(instr[0], -1, instr[0].hashMe())
    }
}

fun String.hashMe(): Int {
    return this.fold(0) { acc, it ->
        (acc + it.code) * 17 % 256
    }
}