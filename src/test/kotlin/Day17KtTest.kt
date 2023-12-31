import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class Day17KtTest : FunSpec({
    val fileContent1 = readFileAsLinesUsingUseLines("17_1.txt")
    test("day17_1") {
        val result = day17_1(fileContent1)
        result.shouldBe(102)
    }

    test("day17_2") {
        val result = day17_2(fileContent1)
        result.shouldBe(94)
    }

    val fileContent2 = readFileAsLinesUsingUseLines("17_2.txt")
    test("day17_2_2") {
        val result = day17_2(fileContent2)
        result.shouldBe(71)
    }


})
