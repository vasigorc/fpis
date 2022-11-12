import sbt._

object Dependencies {

  val scalaTestVersion = "3.2.14"
  val scalaCheckVersion = "1.17.0"
  val commonDependencies: Seq[ModuleID] = Seq(
    "org.scalactic" %% "scalactic" % scalaTestVersion,
    "org.scalatest" %% "scalatest" % scalaTestVersion % "test",
    "org.scalacheck" %% "scalacheck" % scalaCheckVersion % "test"
  )
}