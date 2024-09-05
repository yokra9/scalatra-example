val ScalatraVersion = "3.0.0"
val http4sVersion = "0.23.27"

lazy val root = (project in file("."))
  .settings(
    ThisBuild / scalaVersion := "3.3.3",
    ThisBuild / organization := "com.github.yokra9",
    name := "scalatra-example",
    version := "0.1.0-SNAPSHOT",
    scalacOptions := Seq("-unchecked", "-deprecation"),
    libraryDependencies ++= Seq(
      "org.scalatra" %% "scalatra-jakarta" % ScalatraVersion,
      "org.eclipse.jetty" % "jetty-webapp" % "11.0.23" % "container;compile",
      "jakarta.servlet" % "jakarta.servlet-api" % "5.0.0" % "provided",
      // for Runtime
      "ch.qos.logback" % "logback-classic" % "1.5.7" % Runtime,
      // for Tests
      "org.scalatra" %% "scalatra-scalatest-jakarta" % ScalatraVersion % Test,
      "org.scalatest" %% "scalatest" % "3.2.19" % Test,
      "org.http4s" %% "http4s-dsl" % http4sVersion % Test,
      "org.http4s" %% "http4s-ember-server" % http4sVersion % Test,
      "org.http4s" %% "http4s-ember-client" % http4sVersion % Test
    ),
    dependencyOverrides ++= Seq(
      "org.scala-lang.modules" %% "scala-xml" % "2.1.0"
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
