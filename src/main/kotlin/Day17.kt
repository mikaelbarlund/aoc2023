import java.util.*
import kotlin.math.abs


fun day17_1(fileContent: List<String>): Any {
    val grid = fileContent.toIntMatrix()
    val start = PointWithDirection(0, 0, 'O', 0)
    val goal = PointWithDirection(grid.size - 1, grid[0].size - 1, 'O', 0)
    val aStar =
        AStar(
            grid,
            start,
            goal,
            { a, b -> Pair(a.x, a.y) == Pair(b.x, b.y) },
            ::getNeighbors
        )
    val path = aStar.findPath()
    printPath(grid, path)
    return path.last().g
}

fun day17_2(fileContent: List<String>): Any {
    val grid = fileContent.toIntMatrix()
    val start = PointWithDirection(0, 0, 'O', 0)
    val goal = PointWithDirection(grid.size - 1, grid[0].size - 1, 'O', 0)
    val aStar = AStar(
        grid, start, goal,
        { a, b ->
            Pair(a.x, a.y) == Pair(b.x, b.y) && a.len >= 4
        }, ::getNeighbors2
    )
    val path = aStar.findPath()
    printPath(grid, path)
    return path.last().g
}

private fun printPath(
    grid: List<List<Int>>,
    path: List<Node>
) {
    grid.indices.forEach { x ->
        grid[0].indices.forEach { y ->
            if (y == 0) println()
            val onPath = path.firstOrNull { Pair(it.point.x, it.point.y) == Pair(x, y) }

            if (onPath != null)
                print("\u001b[31m" + onPath.point.dir + "\u001b[0m")
            else
                print(grid[x][y])
        }
    }
    println()
}


data class PointWithDirection(val x: Int, val y: Int, val dir: Char, val len: Int)


class Node(val point: PointWithDirection, var parent: Node?, var g: Double, private var h: Double) {
    fun f(): Double {
        return g + h
    }

    override fun toString(): String {
        return "(${point.x}, ${point.y} ${point.len} $g $h)"
    }
}

class AStar(
    private val grid: List<List<Int>>,
    private val start: PointWithDirection,
    private val goal: PointWithDirection,
    private val isGoal: (PointWithDirection, PointWithDirection) -> Boolean,
    val neighbourFunc: (List<List<Int>>, PointWithDirection) -> List<PointWithDirection>
) {

    private val openSet: PriorityQueue<Node> = PriorityQueue(compareBy { it.f() })
    private val closedSet: MutableSet<PointWithDirection> = mutableSetOf()

    fun findPath(): List<Node> {
        val startNode = Node(start, null, 0.0, heuristic(start, goal))
        openSet.add(startNode)

        while (openSet.isNotEmpty()) {
            val current = openSet.poll()
            if (isGoal(current.point, goal)) {
                return reconstructPath(current)
            }
            closedSet.add(current.point)
            for (neighbor in neighbourFunc(grid, current.point)) {
                if (neighbor in closedSet) {
                    continue
                }
                val tentativeG = current.g + grid[neighbor.x][neighbor.y]
                val newNode = Node(
                    neighbor,
                    current,
                    tentativeG,
                    heuristic(neighbor, goal)
                )
                openSet.add(newNode)
            }
        }
        return emptyList() // No path found
    }

    private fun reconstructPath(node: Node): List<Node> {
        val path = mutableListOf<Node>()
        var current: Node? = node

        while (current != null) {
            path.add(current)
            current = current.parent
        }

        return path.reversed()
    }

    private fun heuristic(a: PointWithDirection, b: PointWithDirection): Double {
        return (abs(a.x - b.x) + abs(a.y - b.y)).toDouble()
    }


}

private fun getNeighbors(grid: List<List<Int>>, point: PointWithDirection): List<PointWithDirection> {
    val neighbors = mutableListOf<PointWithDirection>()
    val dir = point.dir
    val len = point.len
    if (dir != 'v' && point.x > 0 && (dir != '^' || len < 3)) {
        //  turning up
        neighbors.add(PointWithDirection(point.x - 1, point.y, '^', '^'.nextLen(dir, len)))
    }
    if (dir != '^' && point.x < grid.size - 1 && (dir != 'v' || len < 3)) {
        // turning down
        neighbors.add(PointWithDirection(point.x + 1, point.y, 'v', 'v'.nextLen(dir, len)))
    }
    if (dir != '>' && point.y > 0 && (dir != '<' || len < 3)) {
        // turning left
        neighbors.add(PointWithDirection(point.x, point.y - 1, '<', '<'.nextLen(dir, len)))
    }
    if (dir != '<' && point.y < grid[0].size - 1 && (dir != '>' || len < 3)) {
        // turning right
        neighbors.add(PointWithDirection(point.x, point.y + 1, '>', '>'.nextLen(dir, len)))
    }
    return neighbors
}

private fun Char.nextLen(dir: Char, len: Int) = if (this == dir) len + 1 else 1


private fun getNeighbors2(grid: List<List<Int>>, point: PointWithDirection): List<PointWithDirection> {
    val neighbors = mutableListOf<PointWithDirection>()
    val dir = point.dir
    val len = point.len
    if (dir != 'v' && point.x > 0 && (dir != '^' || len < 10) && (dir == 'O' || dir == '^' || len >= 4)) {
        //  turning up
        neighbors.add(PointWithDirection(point.x - 1, point.y, '^', '^'.nextLen(dir, len)))
    }
    if (dir != '^' && point.x < grid.size - 1 && (dir != 'v' || len < 10) && (dir == 'O' || dir == 'v' || len >= 4)) {
        // turning down
        neighbors.add(PointWithDirection(point.x + 1, point.y, 'v', 'v'.nextLen(dir, len)))
    }
    if (dir != '>' && point.y > 0 && (dir != '<' || len < 10) && (dir == 'O' || dir == '<' || len >= 4)) {
        // turning left
        neighbors.add(PointWithDirection(point.x, point.y - 1, '<', '<'.nextLen(dir, len)))
    }
    if (dir != '<' && point.y < grid[0].size - 1 && (dir != '>' || len < 10) && (dir == 'O' || dir == '>' || len >= 4)) {
        // turning right
        neighbors.add(PointWithDirection(point.x, point.y + 1, '>', '>'.nextLen(dir, len)))
    }
    return neighbors
}