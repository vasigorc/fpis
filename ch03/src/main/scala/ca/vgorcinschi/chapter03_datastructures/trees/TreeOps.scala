package ca.vgorcinschi.chapter03_datastructures.trees

import scala.math.Ordered.orderingToOrdered

object TreeOps {
  implicit class OrderedTrees[A: Ordering](tree: Tree[A]) {

    /**
      * Exercise 3.26 Write a function maximum that returns the maximum element in a Tree[Int]. (Note: In Scala you can
      * use x.max (y) to compute the maximum of two integers x and y.
      */

    def maximum: A = {
      maxOneBranch(tree)
    }

    private def maxOneBranch(branch: Tree[A]): A = branch match {
      case Leaf(value) => value
      case Branch(left, right) =>
        val leftResult = maxOneBranch(left)
        val rightResult = maxOneBranch(right)
        if (leftResult > rightResult) leftResult else rightResult
    }
  }
}
