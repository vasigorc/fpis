ThisBuild / organization := "ca.vgorcinschi"
ThisBuild / scalaVersion := "2.13.1"

name := "fpis"

lazy val ch02 = (project in file("ch02"))
  .settings(Common.settings: _*)
  .settings(libraryDependencies ++= Dependencies.commonDependencies)

lazy val root = (project in file("."))
  .aggregate(ch02)