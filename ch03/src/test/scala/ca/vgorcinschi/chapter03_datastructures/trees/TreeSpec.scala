package ca.vgorcinschi.chapter03_datastructures.trees

import ca.vgorcinschi.chapter03_datastructures.trees.TreeSpec.NonEmptyIntTree
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class TreeSpec extends AnyFlatSpec with Matchers {

  behavior of "size"

  it should "correctly count # of leaves" in new NonEmptyIntTree {
    intTree.size should be(9)
  }

  behavior of "maximum"

  it should "correctly find the maximum element" in new NonEmptyIntTree {
    import TreeOps._

    intTree.maximum should be(maxValue)
  }
}

object TreeSpec {
  trait NonEmptyIntTree {
    val maxValue: Int = 40

    val intTree: Branch[Int] = Branch(
      left = Branch(Leaf(7), Leaf(1)),
      right = Branch(
        left = Branch(
          left = Branch(Leaf(32), Leaf(maxValue)),
          right = Branch(left = Branch(Branch(Leaf(1), Leaf(19)), Leaf(8)), right = Leaf(2))
        ),
        right = Leaf(8)
      )
    )
  }
}
