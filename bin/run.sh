sbt/sbt package
$SPARK_HOME/bin/spark-submit --master $1 --deploy-mode $2 --jars $(cat app/target/userJars) --class test.Application $(cat app/target/appJar) $(cat driver/target/driverJar)
