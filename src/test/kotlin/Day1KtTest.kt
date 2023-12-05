import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class Day1KtTest : FunSpec({
    val fileContent1 = readFileAsLinesUsingUseLines("01_1.txt")
    test("day1_1") {
        val result = day1_1(fileContent1)
        result.shouldBe(142)
    }

    val fileContent2 = readFileAsLinesUsingUseLines("01_2.txt")
    test("day1_2") {
        val result = day1_2(fileContent2)
        result.shouldBe(281)
    }
})
