import sbt._
import Keys._

object ApplicationBuild extends Build {

  lazy val defaultSettings = Defaults.defaultSettings ++ Seq(
    version := "1.0",
    scalaVersion := "2.10.3",
    scalacOptions := Seq(
      "-feature",
      "-language:implicitConversions",
      "-language:postfixOps",
      "-unchecked",
      "-deprecation",
      "-encoding", "utf8",
      "-Ywarn-adapted-args"
    ),
    organization := "me.moschops"
  )

  val dependencies = Seq(
    "com.gocardless" % "gocardless-java" % "1.1.0",
    "com.typesafe" % "config" % "1.0.2"
  )

  lazy val root = Project(id = "GoCardlessExample",
    base = file(".")).settings(libraryDependencies ++= dependencies)
}

