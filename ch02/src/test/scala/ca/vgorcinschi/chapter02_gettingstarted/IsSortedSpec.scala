package ca.vgorcinschi.chapter02_gettingstarted

import ca.vgorcinschi.Gens
import org.scalacheck.Properties
import org.scalacheck.Prop.forAll

object IsSortedSpec extends Properties("isSorted") {

  property("isSorted") = forAll(Gens.minIntArraysGen()) { intArray =>
    val sortedArray = intArray.sorted
    isSorted(sortedArray, (i1: Int, i2: Int) => i1 <= i2)
  }
}
