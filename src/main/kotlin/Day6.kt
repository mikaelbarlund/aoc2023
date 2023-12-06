fun day6_1(fileContent: List<String>): Any {
    val input = fileContent
        .map { a -> a.split(":")[1].trim().split("\\s+".toRegex()).map { b -> b.toInt() } }

    val races = (0..<input[0].size)
        .map { Pair(input[0][it], input[1][it]) }

    println(races)
    return races.fold(1) { acc, it ->
        acc * (0..it.first).fold(0) { acc2, it2 ->
            acc2 + if (it2 * (it.first - it2) > it.second) 1 else 0
        }
    }
}


fun day6_2(fileContent: List<String>): Any {
    return 1
}

