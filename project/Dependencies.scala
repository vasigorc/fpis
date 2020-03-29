import sbt._

object Dependencies {

  val scalaTestVersion = "3.1.1"
  val scalaCheckVersion = "1.14.3"
  val commonDependencies: Seq[ModuleID] = Seq(
    "org.scalactic" %% "scalactic" % scalaTestVersion,
    "org.scalatest" %% "scalatest" % scalaTestVersion % "test",
    "org.scalacheck" %% "scalacheck" % scalaCheckVersion % "test"
  )
}