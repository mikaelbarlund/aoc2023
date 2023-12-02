val library = mapOf(
    "red" to 12,
    "green" to 13,
    "blue" to 14,
)

fun day2_1(fileContent: List<String>): Any {
    val x = fileContent
        .map { line -> line.substring(5).split(":") }
        .map { game ->
            Pair(game.first().toInt(), game.last().split(";")
                .map { cubes ->
                    cubes.split(",")
                        .map { sets -> sets.trim().split(" ") }
                        .map { set -> library[set[1]]!! >= set[0].toInt() }
                        .all { it }
                }.all { it }
            )
        }
        .filter { it.second }
        .sumOf { it.first }
    return x
}

fun day2_2(fileContent: List<String>): Any {
    val x = fileContent
        .map { line -> line.substring(5).split(":") }
        .map { game ->
            game.last().split(";")
                .map { cubes ->
                    cubes.split(",")
                        .map { sets -> sets.trim().split(" ") }
                        .map { set ->
                            Pair(
                                (if (set[1] == "red") 0 else if (set[1] == "green") 1 else 2),
                                set[0].toInt()
                            )
                        }
                        .fold(listOf(0, 0, 0)) { acc, foo ->
                            (if (foo.second > acc[foo.first])
                                acc.mapIndexed { i, it -> if (i == foo.first) foo.second else it }
                            else acc)
                        }
                }.reduce { acc, foo ->
                    acc.mapIndexed { i, it ->
                        if (foo[i] > it) foo[i] else it
                    }
                }.reduce { acc, it -> acc * it }

        }.sum()

    return x

}