ThisBuild / organization := "ca.vgorcinschi"
ThisBuild / scalaVersion := "2.13.10"

name := "fpis"

lazy val ch02 = (project in file("ch02"))
  .settings(Common.settings: _*)
  .settings(libraryDependencies ++= Dependencies.commonDependencies)

lazy val ch03 = (project in file("ch03"))
  .settings(Common.settings: _*)
  .settings(libraryDependencies ++= Dependencies.commonDependencies)

lazy val root = (project in file("."))
  .aggregate(ch02, ch03)