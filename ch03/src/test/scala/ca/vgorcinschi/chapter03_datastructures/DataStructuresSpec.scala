package ca.vgorcinschi.chapter03_datastructures

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class DataStructuresSpec extends AnyFlatSpec with Matchers{

  trait NonEmptyCharListFixture {
    val charList: List[Char] = List[Char]('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i')
  }

  trait EmptyCharListFixture {
    val charList: List[Char] = List[Char]()
  }

  behavior of "length"

  it should "return 0 for Nil" in {
    ca.vgorcinschi.chapter03_datastructures.length(Nil) should equal(0)
  }

  it should "return 0 for an empty list" in new EmptyCharListFixture {
    ca.vgorcinschi.chapter03_datastructures.length(charList) should equal(0)
  }

  it should "return right number of entries in a list" in new NonEmptyCharListFixture {
    ca.vgorcinschi.chapter03_datastructures.length(charList) should equal(charList.size)
  }

  behavior of "foldLeft"

  it should "return start value for an empty list" in new EmptyCharListFixture {
    foldLeft(charList, 0L)(_ + _) should equal(0L)
  }

  it should "correctly compute the accumulation" in new NonEmptyCharListFixture {
    foldLeft(charList, "")((acc, elem) => acc + elem) should equal(charList.mkString)
  }

  /**
   * @note that invoking [[foldRight()]] on the same
   *       input does throw a [[StackOverflowError]]
   */
  it should "not throw StackOverFlow for a large passed-in list" in {
    val largeList = List.fill(1_000_000)(0.toByte)
    foldLeft(largeList, 1)(_ & _) should equal(0)
  }

  behavior of "reverse"

  it should "reverse a list of characters" in new NonEmptyCharListFixture {
    reverse(charList) should equal(charList.reverse)
  }

  it should "return an empty list for an empty list input" in new EmptyCharListFixture {
    reverse(charList) should equal(List[Char]())
  }

  it should "return Nil for Nil input" in {
    reverse(Nil) should equal(Nil)
  }
}