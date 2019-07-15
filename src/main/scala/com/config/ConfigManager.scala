package com.config

import java.util.Properties


object ConfigManager {
  private val prop = new Properties()
  // 通过类加载器方法来加载指定的配置文件
  try{
    val dws_user_month = ConfigManager.getClass.getClassLoader.getResourceAsStream("dws_user_month.properties")
    val dm_user_basic = ConfigManager.getClass.getClassLoader.getResourceAsStream("dm_user_basic.properties")
    val dm_user_visit = ConfigManager.getClass.getClassLoader.getResourceAsStream("dm_user_visit.properties")
    val in_basic = ConfigManager.getClass.getClassLoader.getResourceAsStream("basic.properties")

    prop.load(dws_user_month)
    prop.load(dm_user_basic)
    prop.load(dm_user_visit)
    prop.load(in_basic)
  }catch {
    case e:Exception=>e.printStackTrace()
  }
  /**
    * 获取指定Key的对应Value
    */
  def getProper(key:String):String={
    prop.getProperty(key)
  }
}
