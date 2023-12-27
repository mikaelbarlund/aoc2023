fun day19_1(fileContent: List<String>): Any {
    val input = fileContent.fold(Pair(emptyMap<String, Workflow>(), emptyList<Map<Char, Int>>())) { acc, it ->
        if (it.isEmpty()) acc
        else
            Pair(
                if (it[0] == '{') acc.first else {
                    val wf = it.workflow()
                    acc.first + mapOf(wf.id to wf)
                },
                if (it[0] == '{')
                    acc.second + listOf(it.ratings())
                else acc.second,
            )
    }
    val result = input.second.filter { it.checkAcceptance(input.first, "in") == "A" }
    return result.map { it.values.sum() }.sum()
}

private tailrec fun Map<Char, Int>.checkAcceptance(workFlows: Map<String, Workflow>, target: String): String {
    if (target == "R" || target == "A")
        return target
    val wf = workFlows[target]
    val dest = wf!!.rules.fold(Pair(false, wf.default)) { a, i ->
        if (this[i.part]?.compare(i) == true && !a.first) Pair(true, i.target) else a
    }
    return checkAcceptance(workFlows, dest.second)

}


fun day19_2(fileContent: List<String>): Any {

    return 1
}


data class Workflow(val id: String, val rules: List<Rule>, val default: String)

data class Rule(val part: Char, val condition: Char, val limit: Int, val target: String)

private fun String.ratings(): Map<Char, Int> {
    val row = this.substring(1, this.length - 1)
        .split(',')
        .map { q -> q.substring(2).toInt() }
    return mapOf('x' to row[0], 'm' to row[1], 'a' to row[2], 's' to row[3])
}

private fun String.workflow(): Workflow {
    val id = this.substring(0, this.indexOf('{'))
    val default = this.substring(this.lastIndexOf(',') + 1, this.length - 1)
    val rules = this.substring(this.indexOf('{') + 1, this.lastIndexOf(',')).split(',').map { it.toRule() }
    return Workflow(id, rules, default)
}

private fun String.toRule(): Rule {
    return Rule(this[0], this[1], this.substring(2, this.indexOf(':')).toInt(), this.substring(this.indexOf(':') + 1))
}

private fun Int.compare(rule: Rule): Boolean {
    return (rule.condition == '<' && this < rule.limit) || (rule.condition == '>' && this > rule.limit)
}