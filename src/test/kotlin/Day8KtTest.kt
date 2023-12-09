import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class Day8KtTest : FunSpec({
    val fileContent1 = readFileAsLinesUsingUseLines("08_1.txt")
    val fileContent2 = readFileAsLinesUsingUseLines("08_2.txt")
    test("day8_1") {
        val result = day8_1(fileContent1)
        result.shouldBe(2)
    }
    test("day8_1_2") {
        val result = day8_1(fileContent2)
        result.shouldBe(6)
    }


    val fileContent3 = readFileAsLinesUsingUseLines("08_3.txt")
    test("day8_2") {
        val result = day8_2(fileContent3)
        result.shouldBe(6)
    }
})
