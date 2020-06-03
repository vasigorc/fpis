package ca.vgorcinschi.chapter03_datastructures.lists

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class listsSpec extends AnyFlatSpec with Matchers{

  trait NonEmptyCharListFixture {
    val charList: List[Char] = List[Char]('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i')
  }

  trait EmptyCharListFixture {
    val charList: List[Char] = List[Char]()
  }

  behavior of "length"

  it should "return 0 for Nil" in {
    ca.vgorcinschi.chapter03_datastructures.lists.length(Nil) should equal(0)
  }

  it should "return 0 for an empty list" in new EmptyCharListFixture {
    ca.vgorcinschi.chapter03_datastructures.lists.length(charList) should equal(0)
  }

  it should "return right number of entries in a list" in new NonEmptyCharListFixture {
    ca.vgorcinschi.chapter03_datastructures.lists.length(charList) should equal(charList.size)
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

  behavior of "foldLeftViaFoldRight"

  it should "return start value for an empty list" in new EmptyCharListFixture {
    foldLeftViaFoldRight(charList, 0L)(_ + _) should equal(0L)
  }

  it should "correctly compute the accumulation" in new NonEmptyCharListFixture {
    foldLeftViaFoldRight(charList, "")((acc, elem) => acc + elem) should equal(charList.mkString)
  }

  behavior of "foldRightViaFoldLeft"

  it should "return start value for an empty list" in new EmptyCharListFixture {
    foldRightViaFoldLeft(charList, 0L)(_ + _) should equal(0L)
  }

  it should "correctly compute the accumulation" in new NonEmptyCharListFixture {
    foldRightViaFoldLeft(charList, "")((acc, elem) => acc + elem) should equal(charList.mkString)
  }

  /**
   * @note that invoking [[foldRight()]] on the same
   *       input does throw a [[StackOverflowError]]
   */
  it should "not throw StackOverFlow for a large passed-in list" in {
    val largeList = List.fill(1_000_000)(0.toByte)
    foldRightViaFoldLeft(largeList, 1)(_ & _) should equal(0)
  }

  behavior of "append"

  it should "append an element to the end of non empty list" in new NonEmptyCharListFixture {
    val result: List[Char] = append(charList, 'z')
    result.last should equal('z')
    result.size should equal(charList.size + 1)
  }

  it should "return a list of a single element when passed-in an empty list" in new EmptyCharListFixture {
    append(charList, 'z') should equal(List('z'))
  }

  behavior of "concatAll"

  it should "concat three lists in the order that they were passed-in" in {
    val list1 = List[Char]('a', 'b', 'c')
    val list2 = List[Char]('d', 'e', 'f')
    val list3 = List[Char]('g', 'h', 'i')
    val result: List[Char] = concatAll(List(list1, list2, list3))
    result should equal(List[Char]('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i'))
  }

  behavior of "map"

  it should "transform chars in a list to uppercase Strings" in new NonEmptyCharListFixture {
    private val f: Char => String = character => character.toString.toUpperCase
    val uppercased: List[String] = map(charList)(f)
    uppercased should equal(charList.map(f))
  }

  behavior of "flatMap"

  it should "flatten the list after applied function" in {
    flatMap(List(1,2,3))(i => List(i, i)) should equal(List(1,1,2,2,3,3))
  }

  behavior of "zipWith"

  it should "add numbers at same index position from two lists and return result in another list" in {
    zipWith(List(1, 2, 3), List(4, 5, 6))((a, b) => a + b) should equal(List(5, 7, 9))
  }

  it should "return empty list from (A, B) where A is a non-empty list and B is an empty list" in new NonEmptyCharListFixture {
    zipWith(charList, List[Char]())((char1, char2) => char1 + char2) should equal(List[Char]())
  }

  it should "\"zip\" values of two different types into a list of a third type" in {
    val chars = List[Char]('a', 'b', 'c')
    val list = List.tabulate(5)(_ * 1)
    val result = zipWith(chars, list)((c, i) => s"$i$c")
    result should equal(List[String]("0a", "1b", "2c"))
  }

  behavior of "kSubsequences"

  it should "return a list of k subsequences for non empty char list" in new NonEmptyCharListFixture {
    kSubsequences(charList, 3) should contain allElementsOf (List(
      List('a', 'b', 'c'),
      List('b', 'c', 'd'),
      List('c', 'd', 'e'),
      List('d', 'e', 'f'),
      List('e', 'f', 'g'),
      List('f', 'g', 'h'),
      List('g', 'h', 'i')
    ))
  }

  behavior of "hasSubsequence"

  it should "return true if subsequence is contained" in new NonEmptyCharListFixture {
    hasSubsequence(charList, List[Char]('d', 'e', 'f')) should be(true)
  }

  it should "return false if subsequence isn't contained" in new NonEmptyCharListFixture {
    hasSubsequence(charList, List[Char]('s', 'z', 'x')) should be(false)
  }
}