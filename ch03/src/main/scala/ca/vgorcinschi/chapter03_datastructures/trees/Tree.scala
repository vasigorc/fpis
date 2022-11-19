package ca.vgorcinschi.chapter03_datastructures.trees

import cats.Eval

sealed trait Tree[+A] {

  /**
    * Exercise 3.25 Write a function that counts the number of nodes (leaves and branches) in a tree.
    * @return
    */
  def size: Int

  /**
    * Exercise 3.27 Write a function depth that returns the maximum path length from the root of a tree to any leaf.
    */
  def depth: Int
}
case class Leaf[A](value: A) extends Tree[A] {
  override val size: Int = 1

  override def depth: Int = 1
}
case class Branch[A](left: Tree[A], right: Tree[A]) extends Tree[A] {
  override def size: Int = {
    def oneBranchSize(branch: Tree[A]) = branch match {
      case leaf @ Leaf(_) => leaf.size
      case Branch(l, r)   => l.size + r.size
    }
    oneBranchSize(left) + oneBranchSize(right)
  }

  override def depth: Int = {

    /**
      * Helper method that relies on [[Eval.defer]] to avoid [[StackOverflowError]] by creating a chain of function
      * objects on the heap (memory) - we are thus limited by the "size of the heap rather than the stack" (from
      * [[https://www.scalawithcats.com/dist/scala-with-cats.html Scala with cats]])
      * @param branch
      *   branch to evaluate
      * @return
      *   [[Eval]] instance containing the longest depth of a branch
      */
    def depthHelper(branch: Tree[A]): Eval[Int] = branch match {
      case Leaf(_) => Eval.One
      case Branch(left, right) =>
        for {
          resultLeft <- Eval.defer(depthHelper(left).map(_ + 1))
          resultRight <- Eval.defer(depthHelper(right).map(_ + 1))
        } yield math.max(resultRight, resultLeft)
    }

    depthHelper(this).value
  }
}
