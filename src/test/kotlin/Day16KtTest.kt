import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class Day16KtTest : FunSpec({
    val fileContent1 = readFileAsLinesUsingUseLines("16_1.txt")
    test("day16_1") {
        val result = day16_1(fileContent1)
        result.shouldBe(46)
    }

    test("day16_2") {
        val result = day16_2(fileContent1)
        result.shouldBe(51)
    }


})
