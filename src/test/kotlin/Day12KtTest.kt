import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class Day12KtTest : FunSpec({
    val fileContent1 = readFileAsLinesUsingUseLines("12_1.txt")
    test("day12_1") {
        val result = day12_1(fileContent1)
        result.shouldBe(21)
    }

    test("day12_2") {
        val result = day12_2(fileContent1)
        result.shouldBe(525152)
    }

    test("pairs") {
        val result = unfoldPairs(listOf(".# 1"))
        result.shouldBe(listOf(Pair(".#?.#?.#?.#?.#", listOf(1, 1, 1, 1, 1))))
    }
})
