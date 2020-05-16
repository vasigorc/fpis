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
}