import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class Day3KtTest : FunSpec({
    val testData = listOf(
        "467..114..",
        "...*......",
        "..35..633.",
        "......#...",
        "617*......",
        ".....,.58.",
        "..592.....",
        "......755.",
        "...$.*....",
        ".664.598.."
    )

    test("day3_1firstGo") {
        val result = day3_1firstGo(testData)
        result.shouldBe(4361)
    }
    test("day3_1") {
        val result = day3_1(testData)
        result.shouldBe(4361)
    }

    test("day3_2") {
        val result = day3_2(testData)
        result.shouldBe(467835)
    }

})
