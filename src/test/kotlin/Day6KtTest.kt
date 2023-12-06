import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class Day6KtTest : FunSpec({
    val fileContent1 = readFileAsLinesUsingUseLines("06_1.txt")
    test("day6_1") {
        val result = day6_1(fileContent1)
        result.shouldBe(288)
    }

    test("day6_2") {
        val result = day6_2(fileContent1)
        result.shouldBe(71503)
    }
})
