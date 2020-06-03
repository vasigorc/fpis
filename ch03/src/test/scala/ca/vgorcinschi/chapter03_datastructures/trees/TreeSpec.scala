package ca.vgorcinschi.chapter03_datastructures.trees

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class TreeSpec extends AnyFlatSpec with Matchers {

  trait NonEmptyIntTree {
    val intTree: Branch[Int] = Branch(left = Branch(
      Leaf(7), Leaf(1)
    ), right = Branch(
      left = Branch(
        left = Branch(Leaf(32), Leaf(40)),
        right = Branch(
          left = Branch(Branch(Leaf(1), Leaf(19)), Leaf(8)),
          right = Leaf(2))),
      right = Leaf(8)
    ))
  }

  behavior of "size"

  it should "correctly count # of leaves" in new NonEmptyIntTree {
    intTree.size should be(9)
  }
}