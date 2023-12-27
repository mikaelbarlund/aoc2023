import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class Day19KtTest : FunSpec({
    val fileContent1 = readFileAsLinesUsingUseLines("19_1.txt")
    test("day19_1") {
        val result = day19_1(fileContent1)
        result.shouldBe(19114)
    }


    test("day19_2") {
        val result = day19_2(fileContent1)
        result.shouldBe(952408144115)
    }


})
