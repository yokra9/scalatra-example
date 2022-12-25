val ScalatraVersion = "2.8.+"
val http4sVersion = "0.23.16"

lazy val root = (project in file("."))
  .settings(
    ThisBuild / scalaVersion := "2.13.8",
    ThisBuild / organization := "com.github.yokra9",
    name := "scalatra-example",
    version := "0.1.0-SNAPSHOT",
    scalacOptions := Seq("-unchecked", "-deprecation"),
    libraryDependencies ++= Seq(
      "org.scalatra" %% "scalatra" % ScalatraVersion,
      "org.eclipse.jetty" % "jetty-webapp" % "10.0.13",
      // for Runtime
      "ch.qos.logback" % "logback-classic" % "1.4.5" % Runtime,
      // for Tests
      "org.scalatra" %% "scalatra-scalatest" % ScalatraVersion % Test,
      "org.scalatest" %% "scalatest" % "3.2.14" % Test,
      "org.http4s" %% "http4s-dsl" % http4sVersion % Test,
      "org.http4s" %% "http4s-ember-server" % http4sVersion % Test,
      "org.http4s" %% "http4s-ember-client" % http4sVersion % Test
    ),
    assembly / mainClass := Some("Main"),
    ThisBuild / assemblyMergeStrategy := {
      case "module-info.class" => MergeStrategy.discard
      case x =>
        val oldStrategy = (assembly / assemblyMergeStrategy).value
        oldStrategy(x)
    },
    Docker / packageName := "sample-webapp",
    Docker / version := "2.0.0",
    dockerBaseImage := "eclipse-temurin:latest",
    dockerExposedPorts := List(8080)
  )

enablePlugins(SbtTwirl)
enablePlugins(JettyPlugin)
enablePlugins(JavaAppPackaging)
enablePlugins(DockerPlugin)
