package ca.vgorcinschi.chapter03_datastructures

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class DataStructuresSpec extends AnyFlatSpec with Matchers{

  behavior of "length"

  it should "return 0 for Nil" in {
    ca.vgorcinschi.chapter03_datastructures.length(Nil) should equal(0)
  }

  it should "return 0 for an empty list" in {
    ca.vgorcinschi.chapter03_datastructures.length(List[Char]()) should equal(0)
  }

  it should "return 5 for a list of size 5" in {
    ca.vgorcinschi.chapter03_datastructures.length(List[Int](1,2,3,4,5)) should equal(5)
  }

  behavior of "foldLeft"

  it should "return start value for an empty list" in {
    foldLeft(List[Int](), 0L)(_ + _) should equal(0L)
  }

  it should "correctly compute the accumulation"in {
    val experiment = List[Char]('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i')
    foldLeft(experiment, "")((acc, elem) => acc + elem) should equal(experiment.mkString)
  }

  /**
   * @note that invoking [[foldRight()]] on the same
   *       input does throw a [[StackOverflowError]]
   */
  it should "not throw StackOverFlow for a large passed-in list" in {
    val largeList = List.fill(1_000_000)(0.toByte)
    foldLeft(largeList, 1)(_ & _) should equal(0)
  }
}