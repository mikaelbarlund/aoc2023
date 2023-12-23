import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class Day18KtTest : FunSpec({
    val fileContent1 = readFileAsLinesUsingUseLines("18_1.txt")
    test("day18_1") {
        val result = day18_1(fileContent1)
        result.shouldBe(62)
    }
    test("day18_1_2") {
        val result = day18_1_2(fileContent1)
        result.shouldBe(62)
    }

    test("day18_2") {
        val result = day18_2(fileContent1)
        result.shouldBe(952408144115)
    }


})
