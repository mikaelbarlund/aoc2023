import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class Day10KtTest : FunSpec({
    val fileContent1 = readFileAsLinesUsingUseLines("10_1.txt")
    test("day10_1") {
        val result = day10_1(fileContent1)
        result.shouldBe(8)
    }

    test("day10_2") {
        val result = day10_2(fileContent1)
        result.shouldBe(1)
    }

    val fileContent2 = readFileAsLinesUsingUseLines("10_2.txt")
    test("day10_2_2") {
        val result = day10_2(fileContent2)
        result.shouldBe(8)
    }
})
