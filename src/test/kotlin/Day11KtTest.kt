import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class Day11KtTest : FunSpec({
    val fileContent1 = readFileAsLinesUsingUseLines("11_1.txt")
    test("day11_1") {
        val result = day11_1(fileContent1)
        result.shouldBe(374)
    }

    test("day11_2") {
        val result = day11_2(fileContent1,2L)
        result.shouldBe(374)
    }
    test("day11_2_2") {
        val result = day11_2(fileContent1,10L)
        result.shouldBe(1030)
    }
    test("day11_2_3") {
        val result = day11_2(fileContent1,100L)
        result.shouldBe(8410)
    }


})
