spark-class-loading-test
========================

This project tests jar distribution and class loading in different deploy modes.
Set `SPARK_HOME` environment variable and then run the following command to start a test:

~~~
bin/run.sh <master> <deploy-mode>
~~~

It uses `spark-submit` to launch a job with an app jar, a user jar, and a jar to be loaded dynamically:

~~~
spark-submit --master <master> --deploy-mode <deploy-mode> \
  --jars loader.jar --class test.Application app.jar driver.jar
~~~

Inside the main class `test.Application`, `driver.jar` is added dynamically via `sc.addJar`, 
then we run `Loader.load("test.Driver")` on the master and then on executors.

