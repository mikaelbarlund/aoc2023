import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class Day7KtTest : FunSpec({
    val fileContent1 = readFileAsLinesUsingUseLines("07_1.txt")
    test("day7_1") {
        val result = day7_1(fileContent1)
        result.shouldBe(35)
    }

    test("day7_2") {
        val result = day7_2(fileContent1)
        result.shouldBe(46)
    }
})
