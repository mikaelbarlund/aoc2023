import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class Day1KtTest : FunSpec({

    test("day1_1") {
        val testData = listOf(
            "1abc2",
            "pqr3stu8vwx",
            "a1b2c3d4e5f",
            "treb7uchet"
        )
        val result = day1_1(testData)
        result.shouldBe(142)
    }

    test("day1_2") {
        val testData = listOf(
            "two1nine",
            "eightwothree",
            "abcone2threexyz",
            "xtwone3four",
            "4nineeightseven2",
            "zoneight234",
            "7pqrstsixteen"
        )
        val result = day1_2(testData)
        result.shouldBe(281)
    }
})
