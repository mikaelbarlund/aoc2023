import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class Day7KtTest : FunSpec({
    val fileContent1 = readFileAsLinesUsingUseLines("07_1.txt")
    test("day7_1") {
        val result = day7_1(fileContent1)
        result.shouldBe(6440)
    }

    test("handToStrengthJoker") {
        handToStrengthJoker(listOf('a', 'a', 'a', 'a', 'a')).first.shouldBe(7)
        handToStrengthJoker(listOf('a', 'a', 'a', 'a', 'b')).first.shouldBe(7)
        handToStrengthJoker(listOf('a', 'a', 'a', 'b', 'b')).first.shouldBe(7)
        handToStrengthJoker(listOf('a', 'b', 'b', 'b', 'b')).first.shouldBe(7)
        handToStrengthJoker(listOf('a', 'a', 'a', 'b', 'd')).first.shouldBe(6)
        handToStrengthJoker(listOf('a', 'a', 'b', 'b', 'd')).first.shouldBe(6)
        handToStrengthJoker(listOf('a', 'a', 'b', 'b', 'd')).first.shouldBe(6)
        handToStrengthJoker(listOf('a', 'b', 'b', 'b', 'd')).first.shouldBe(6)
        handToStrengthJoker(listOf('a', 'b', 'b', 'd', 'd')).first.shouldBe(5)
        handToStrengthJoker(listOf('b', 'b', 'b', 'd', 'd')).first.shouldBe(5)
        handToStrengthJoker(listOf('a', 'a', 'b', 'c', 'd')).first.shouldBe(4)
        handToStrengthJoker(listOf('a', 'b', 'b', 'c', 'd')).first.shouldBe(4)
    }

    test("handToStrength") {
        handToStrength(listOf('a', 'a', 'a', 'a', 'a')).first.shouldBe(7)
        handToStrength(listOf('a', 'a', 'a', 'a', 'b')).first.shouldBe(6)
        handToStrength(listOf('a', 'a', 'a', 'b', 'b')).first.shouldBe(5)
        handToStrength(listOf('a', 'a', 'a', 'b', 'c')).first.shouldBe(4)
        handToStrength(listOf('a', 'a', 'b', 'b', 'c')).first.shouldBe(3)
        handToStrength(listOf('a', 'a', 'b', 'c', 'd')).first.shouldBe(2)
        handToStrength(listOf('a', 'b', 'c', 'd', 'e')).first.shouldBe(1)
    }

    test("day7_2") {
        val result = day7_2(fileContent1)
        result.shouldBe(5905)
    }
})
