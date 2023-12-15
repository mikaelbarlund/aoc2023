import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class Day14KtTest : FunSpec({
    val fileContent1 = readFileAsLinesUsingUseLines("14_1.txt")
    test("day14_1") {
        val result = day14_1(fileContent1)
        result.shouldBe(136)
    }

    test("day14_2") {
        val result = day14_2(fileContent1)
        result.shouldBe(64)
    }

})
