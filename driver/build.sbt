name := "driver"

Keys.`package` <<=
  (baseDirectory, packagedArtifact in (Compile, packageBin), Keys.`package` in Compile).map { (base, art, p) =>
    IO.write(base / "target" / "driverJar", art._2.getPath)
    p
  }
