name := "app"

autoScalaLibrary := false

libraryDependencies += "org.scala-lang" % "scala-library" % scalaVersion.value % "provided"

libraryDependencies += "org.apache.spark" %% "spark-core" % "1.0.0" % "provided"

libraryDependencies += "com.google.guava" % "guava" % "14.0.1"

Keys.`package` <<=
  (baseDirectory, packagedArtifact in (Compile, packageBin), dependencyClasspath in Runtime,
    Keys.`package` in Compile).map { (base, art, rcp, p) =>
      IO.write(base / "target" / "appJar", "file:" + art._2.getPath)
      IO.write(base / "target" / "userJars", (rcp).files.map(x => "file:" + x).mkString(","))
      p
  }
