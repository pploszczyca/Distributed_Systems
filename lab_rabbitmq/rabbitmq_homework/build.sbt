ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "3.1.1"

lazy val root = (project in file("."))
  .settings(
    name := "rabbitmq_homework",
    idePackagePrefix := Some("agh.edu.pl")
  )
