import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class Day2KtTest : FunSpec({
    val testData = listOf(
        "Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green",
        "Game 2: 1 blue, 2 green; 3 green, 4 blue, 1 red; 1 green, 1 blue",
        "Game 3: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red",
        "Game 4: 1 green, 3 red, 6 blue; 3 green, 6 red; 3 green, 15 blue, 14 red",
        "Game 5: 6 red, 1 blue, 3 green; 2 blue, 1 red, 2 green"
    )

    test("day2_1") {
        val result = day2_1(testData)
        result.shouldBe(8)
    }

    test("day2_2") {
        val result = day2_2(testData)
        result.shouldBe(2286)
    }
})
