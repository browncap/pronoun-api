scalaVersion in ThisBuild := "2.12.10"

name := "pronoun"

val http4sVersion     = "0.21.2"
val catsVersion       = "1.6.0"
val catsEffectVersion = "1.3.0"
val circeVersion      = "0.12.2"
val specs2Version     = "4.7.0"
val scalaTestVersion  = "3.0.8"
val pureConfigVersion = "0.10.2"
val fs2Version        = "2.1.0"

enablePlugins(JavaAppPackaging)

libraryDependencies ++= Seq(
  "org.http4s"            %% "http4s-dsl"           % http4sVersion,
  "org.http4s"            %% "http4s-blaze-server"  % http4sVersion,
  "org.http4s"            %% "http4s-blaze-client"  % http4sVersion,
  "org.typelevel"         %% "cats-core"            % catsVersion,
  "org.typelevel"         %% "cats-effect"          % catsEffectVersion,
  "io.circe"              %% "circe-generic"        % circeVersion,
  "io.circe"              %% "circe-generic-extras" % circeVersion,
  "io.circe"              %% "circe-parser"         % circeVersion,
  "io.circe"              %% "circe-refined"        % circeVersion,
  "org.http4s"            %% "http4s-circe"         % http4sVersion,
  "org.scalatest"         %% "scalatest"            % scalaTestVersion % Test,
  "org.specs2"            %% "specs2-core"          % specs2Version % Test,
  "org.specs2"            %% "specs2-matcher"       % specs2Version % Test,
  "org.specs2"            %% "specs2-scalacheck"    % specs2Version % Test,
  "com.github.pureconfig" %% "pureconfig"           % pureConfigVersion,
  "com.github.pureconfig" %% "pureconfig-http4s"    % pureConfigVersion,
  "co.fs2"                %% "fs2-io"               % fs2Version
)

scalacOptions ++= Seq("-Ypartial-unification")
