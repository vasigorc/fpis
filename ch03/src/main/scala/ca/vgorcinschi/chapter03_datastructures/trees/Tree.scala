package ca.vgorcinschi.chapter03_datastructures.trees

sealed trait Tree[+A] {
  /**
   * Exercise 3.25 Write a function that counts the number of nodes (leaves and branches) in a tree.
   * @return
   */
  def size: Int
}
case class Leaf[A](value: A) extends Tree[A] {
  override val size: Int = 1
}
case class Branch[A](left: Tree[A], right: Tree[A]) extends Tree[A] {
  override def size: Int = {
   def oneBranchSize(branch: Tree[A]) = branch match {
     case leaf @ Leaf(_) => leaf.size
     case Branch(l, r) => l.size + r.size
   }
    oneBranchSize(left) + oneBranchSize(right)
  }
}