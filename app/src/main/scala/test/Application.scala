package test

import com.google.common.base.Throwables

import org.apache.spark.{SparkConf, SparkContext}

object Application {

  def main(args: Array[String]) {
    if (args.length < 1) {
      println("Must provide the path to the driver jar.")
      sys.exit(1)
    }
    val conf = new SparkConf().setAppName("ClassLoadingTest")
    val sc = new SparkContext(conf)
    sc.addJar(args(0))
    var driverMsg: String = null
    try {
      Loader.load("test.Driver")
      driverMsg = "Successfully loaded driver on master."
    } catch {
      case e: Throwable =>
        driverMsg = s"Error loading driver on master: ${Throwables.getStackTraceAsString(e)}"
    }
    val messages = sc.parallelize(0 until 2, 2).map { i =>
      var msg: String = null
      try {
        Loader.load("test.Driver")
        msg = s"Successfully loaded driver on partition $i"
      } catch {
        case e: Throwable =>
          msg = s"Error loading driver on partition $i: ${Throwables.getStackTraceAsString(e)}"
      }
      msg
    }.collect()
    println(driverMsg)
    messages.foreach(println)
    sc.stop()
  }
}
