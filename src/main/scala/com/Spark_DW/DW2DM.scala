package Spark_DW

import com.Constants.Constan
import com.Utils.JDBCUtils
import com.config.ConfigManager
import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.sql.{DataFrame, SaveMode, SparkSession}
import org.apache.spark.sql.hive.HiveContext

object DW2DM {
  //表名称
  val kTable = "qfbap_dm.dm_user_basic"
  //时间
  val kDate = "2019-07-12"


  def main(args: Array[String]): Unit = {
    //System.setProperty("hadoop.home.dir", "")
    System.setProperty("HADOOP_USER_NAME", "root")
    //val spark = SparkSession.builder().appName("DW2DM").master("local").getOrCreate()  //不能用session，会出错
    val conf = new SparkConf().setAppName(Constan.SPARK_APP_NAME_USER).setMaster(Constan.SPARK_LOACL)
    val sc = new SparkContext(conf)
    val hiveContext = new HiveContext(sc)

    // 处理配置参数
    val jdbcProp = JDBCUtils.getJdbcProp()._1
    val jdbcUrl = JDBCUtils.getJdbcProp()._2

    //加载sql
    val sql = ConfigManager.getProper(kTable)
    println(sql)
    val result: DataFrame = hiveContext.sql(sql)
    //result.show()
    //写入hive
    //result.write.mode(SaveMode.Overwrite).insertInto(kTable)
    //存入mysql,不能使用overwrite
    result.write.mode(SaveMode.Append).jdbc(jdbcUrl,kTable, jdbcProp)

  }
}
