import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class Day5KtTest : FunSpec({
    val fileContent1 = readFileAsLinesUsingUseLines("05_1.txt")
    test("day5_1") {
        val result = day5_1(fileContent1)
        result.shouldBe(35)
    }

    test("day5_2") {
        val result = day5_2(fileContent1)
        result.shouldBe(46)
    }
})
