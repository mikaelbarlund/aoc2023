import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class Day13KtTest : FunSpec({
    val fileContent1 = readFileAsLinesUsingUseLines("13_1.txt")
    test("day13_1") {
        val result = day13_1(fileContent1)
        result.shouldBe(405)
    }

    val fileContent2 = readFileAsLinesUsingUseLines("13_2.txt")
    test("day13_1_2") {
        val result = day13_1(fileContent2)
        result.shouldBe(101)
    }

    test("day13_2") {
        val result = day13_2(fileContent1)
        result.shouldBe(400)
    }

    val fileContent3 = readFileAsLinesUsingUseLines("13_3.txt")
    test("day13_2_10") {
        val result = day13_1(fileContent3)
        result.shouldBe(1400)
    }
    test("day13_2_1") {// 11,6
        val result = day13_2(fileContent3)
        result.shouldBe(7)
    }

    val fileContent4 = readFileAsLinesUsingUseLines("13_4.txt")
    test("day13_4") {
        val result = day13_1(fileContent4)
        result.shouldBe(1)
    }
    test("day13_4_2") {// 11,6
        val result = day13_2(fileContent4)
        result.shouldBe(10)
    }

})
