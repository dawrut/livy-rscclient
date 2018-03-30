name := "spark-livy"

version := "0.1"

scalaVersion := "2.11.12"

libraryDependencies ++= Seq(
  "org.apache.spark" % "spark-core_2.11" % "2.3.0",
  "org.apache.livy" % "livy-client-http" % "0.5.0-incubating"
)