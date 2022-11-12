package ca.vgorcinschi

import org.scalacheck.Gen
import org.scalacheck.Gen.containerOf

object Gens {

  def minIntArraysGen(lowerBound: Int = 0, upperBound: Int = 100): Gen[Array[Int]] =
    containerOf[Array, Int](Gen.chooseNum(lowerBound, upperBound))
}
