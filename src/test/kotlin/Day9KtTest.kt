import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class Day9KtTest : FunSpec({
    val fileContent1 = readFileAsLinesUsingUseLines("09_1.txt")
    test("day9_1") {
        val result = day9_1(fileContent1)
        result.shouldBe(114)
    }

    test("day9_2") {
        val result = day9_2(fileContent1)
        result.shouldBe(6)
    }
})
