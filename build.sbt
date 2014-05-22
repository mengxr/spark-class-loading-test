organization in ThisBuild := "com.github.mengxr"

scalaVersion in ThisBuild := "2.10.4"

version in ThisBuild := "0.1"

exportJars in ThisBuild := true

lazy val loader = project in file("loader")

lazy val driver = project in file("driver")

lazy val app = project in file("app") dependsOn (loader)

lazy val all = project in file(".") aggregate(loader, driver, app)
