import kotlin.math.abs

fun day18_1(fileContent: List<String>): Any { //62365
    val digs = fileContent
        .map { Dig(it[0], it.substring(2, it.indexOf('(') - 1).toLong()) }
    val border = findBorder(digs)
    val content = floodFill(listOf(Point(1, 1)), border, emptyList<Point>())
    return (border + content).size - 1
}

fun day18_1_2(fileContent: List<String>): Any {

    val digs = fileContent
        .map { Dig(it[0], it.substring(2, it.indexOf('(') - 1).toLong()) }
    val border = digs.corners()
    val result = border.shoelace()
    return result + digs.sumOf { it.dist } / 2 + 1
}

fun day18_2(fileContent: List<String>): Any {

    val digs = fileContent
        .map { Dig(it[it.indexOf(')') - 1], it.substring(it.indexOf('#') + 1, it.indexOf(')') - 1).toLong(16)) }
    val border = digs.corners()
    val result = border.shoelace()
    return result + digs.sumOf { it.dist } / 2 + 1
}

private fun findBorder(digs: List<Dig>): List<Point> {
    val border = digs
        .fold(listOf(Point(0, 0))) { a, i ->
            a + (1..i.dist).map { j ->
                Point(
                    a.last().x + j * i.dir.dirX(),
                    a.last().y + j * i.dir.dirY()
                )
            }
        }
    return border
}

private fun List<Dig>.corners(): List<Point> {
    val border = this
        .fold(listOf(Point(0, 0))) { a, i ->
            a + listOf(
                Point(
                    a.last().x + i.dist * i.dir.dirX(),
                    a.last().y + i.dist * i.dir.dirY()
                )
            )
        }
    return border
}

tailrec fun floodFill(todo: List<Point>, border: List<Point>, visited: List<Point>): List<Point> {
    return if (todo.isEmpty())
        visited
    else
        floodFill(
            todo.subList(1, todo.size) + todo[0].neighbors().filter { it.isValid(border, visited, todo) },
            border,
            visited + listOf(todo[0])
        )
}

data class Constraint2D(val xMin: Long, val xMax: Long, val yMin: Long, val yMax: Long)

private fun Char.dirX(): Long {
    return when {
        listOf('U', '3').contains(this) -> 0
        listOf('R', '0').contains(this) -> 1
        listOf('D', '1').contains(this) -> 0
        else -> -1
    }
}

private fun Char.dirY(): Long {
    return when {
        listOf('U', '3').contains(this) -> -1
        listOf('R', '0').contains(this) -> 0
        listOf('D', '1').contains(this) -> 1
        else -> 0
    }
}

data class Dig(val dir: Char, val dist: Long)

data class Point(val x: Long, val y: Long)

private fun Point.neighbors(): List<Point> {
    return listOf(
        Point(this.x + 1, this.y),
        Point(this.x - 1, this.y),
        Point(this.x, this.y + 1),
        Point(this.x, this.y - 1),
    )
}

private fun Point.isValid(border: List<Point>, visited: List<Point>, todo: List<Point>): Boolean {
    return !(border.contains(this) || visited.contains(this) || todo.contains(this))
}


private fun List<Point>.constraint2D(): Constraint2D {
    val x = this.map { it.x }
    val y = this.map { it.y }
    return Constraint2D(x.min(), x.max(), y.min(), y.max())
}

private fun List<Point>.printPath() {
    val constraint = this.constraint2D()
    (constraint.xMin..constraint.xMax).map { x ->
        (constraint.yMin..constraint.yMax).map { y ->
            if (y == constraint.yMin) println()
            val onPath = this.firstOrNull { Pair(it.x, it.y) == Pair(x, y) }
            if (onPath != null)
                print("\u001b[31m#\u001b[0m")
            else
                print('.')
        }
    }
    println()
}

tailrec fun List<Point>.shoelace(first: Point = this[0], sum: Long = 0L): Long {
    return if (this.size == 1)
        abs(sum + this[0].x * first.y - first.x * this[0].y) / 2
    else
        this.subList(1, this.size).shoelace(first, sum + this[0].x * this[1].y - this[1].x * this[0].y)
}
