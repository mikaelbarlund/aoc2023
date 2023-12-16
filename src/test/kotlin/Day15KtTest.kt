import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class Day15KtTest : FunSpec({
    val fileContent1 = readFileAsLinesUsingUseLines("15_1.txt")
    test("day15_1") {
        val result = day15_1(fileContent1)
        result.shouldBe(1320)
    }

    test("day15_2") {
        val result = day15_2(fileContent1)
        result.shouldBe(145)
    }
    test("hashMe"){
        "HASH".hashMe().shouldBe(52)
        "rn".hashMe().shouldBe(0)
        "cm".hashMe().shouldBe(0)
        "qp".hashMe().shouldBe(1)
        "pc".hashMe().shouldBe(3)
    }

})
