fun day8_1(fileContent: List<String>): Any {
    val path = fileContent[0]
    val instructions = fileContent.slice(2..<fileContent.size)
        .map { it.split(" = ") }
        .map { Pair(it[0], it[1].slice(1..8).split(", ")) }
        .associateBy({ it.first }, { Pair(it.second[0], it.second[1]) })
    return travel("AAA", path, instructions, 0, 0)
}

tailrec fun travel(
    current: String,
    path: String,
    instructions: Map<String, Pair<String, String>>,
    distance: Int,
    pathIndex: Int
): Any {
    return if (current == "ZZZ") distance
    else travel(
        if (path[pathIndex] == 'L') instructions.get(current)!!.first else instructions.get(current)!!.second,
        path,
        instructions,
        distance + 1,
        (pathIndex + 1) % path.length,
    )
}

fun day8_2(fileContent: List<String>): Any {
    val path = fileContent[0].toCharArray().map { if (it == 'L') 0 else 1 }
    val instructions = fileContent.slice(2..<fileContent.size)
        .map { it.split(" = ") }
        .map { Pair(it[0], it[1].slice(1..8).split(", ")) }
        .associateBy({ it.first }, { listOf(it.second[0], it.second[1]) })
    val start = instructions.keys.filter { it[2] == 'A' }
    return start.map { travelFast(it, 0, instructions, 0, path, path.size) }
        .reduce { a, i -> lcm(a, i) }
}

fun lcm(a: Long, b: Long): Long {
    return a / gcd(a, b) * b
}

fun gcd(a: Long, b: Long): Long {
    return if (b == 0L) a else gcd(b, a % b)
}

tailrec fun travelFast(
    current: String,
    pathIndex: Int,
    instructions: Map<String, List<String>>,
    distance: Long,
    path: List<Int>,
    length: Int
): Long {
    return if (current[2] == 'Z') distance
    else travelFast(
        instructions.get(current)!![path[pathIndex]],
        (pathIndex + 1) % length,
        instructions,
        distance + 1,
        path,
        length
    )
}
