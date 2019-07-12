package Spark_DW

import org.apache.spark.sql.{DataFrame, SparkSession}
import org.apache.spark.sql.hive.HiveContext

object DW2DM {
  def main(args: Array[String]): Unit = {

    //System.setProperty("hadoop.home.dir", "")
    //System.setProperty("HADOOP_USER_NAME", "root")
    val spark = SparkSession.builder().appName("DW2DM").master("local[2]").getOrCreate()
    val hiveContext = new HiveContext(spark.sparkContext)


    val result: DataFrame = hiveContext.sql("sql")

  }
}
