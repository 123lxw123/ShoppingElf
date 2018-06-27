package com.lxw.shoppingelf.util


import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type
import java.util.*
import kotlin.collections.ArrayList

object GsonUtil {

    /**
     * 使用json转换成实体Bean
     *
     * @param json json数据字符串
     * @param className 类名，为了和JSON对接
     * @return 返回这个类的实体类
     */
    fun <T> json2Bean(json: String, className: Class<T>): T? {
        var t: T? = null
        try {
            val gson = Gson()
            t = gson.fromJson(json, className)
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return t
    }


    /**
     * 使用json转换成实体Bean
     *
     * @param json json数据字符串
     * @param typeOfT 类型，为了和JSON对接
     * @return 返回这个类的实体类
     */
    fun <T> json2Bean(json: String, typeOfT: Type): T? {
        var t: T? = null
        try {
            val gson = Gson()
            t = gson.fromJson(json, typeOfT)
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return t
    }


    /**
     * 使用json转换成List
     *
     * @param json json格式字符串
     * @param className  类名
     * @return 返回一个list
     */
    fun <T> json2List(json: String, className: Class<T>): List<T> {
        var list: List<T> = ArrayList()
        try {
            val gson = Gson()
            list = gson.fromJson(json, object : TypeToken<List<T>>() {

            }.getType())
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return list

    }

    /**
     * 返回Map<String></String>,T>
     *
     * @param json json数据字符串
     * @param className 类名
     * @return 返回一个Map类型
     */
    fun <T> json2Map(json: String, className: Class<T>): Map<String, T> {
        var map: Map<String, T> = HashMap()
        try {
            val gson = Gson()
            map = gson.fromJson(json, object : TypeToken<Map<String, T>>() {

            }.getType())
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return map
    }

    /**
     * 返回Set<T>
     *
     * @param json 一个json格式数据
     * @param className 匹配的数据类型
     * @return 返回这个类型的集合Set
    </T> */
    fun <T> json2Set(json: String, className: Class<T>): Set<T> {
        var set: Set<T> = HashSet()
        try {
            val gson = Gson()
            set = gson.fromJson(json, object : TypeToken<Set<T>>() {

            }.getType())
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return set
    }

    /**
     * 返回List<Mao></Mao><String></String>,T>>
     * @param json 一个json格式数据
     * @param className 匹配的数据类型
     * @return 返回这个类型的集合Set
     */

    fun <T> listKeyMaps(json: String, className: Class<T>): List<Map<String, T>> {
        var list: List<Map<String, T>> = ArrayList()
        try {
            val gson = Gson()
            list = gson.fromJson(json,
                    object : TypeToken<List<Map<String, T>>>() {

                    }.getType())
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return list
    }

    /**
     * 将实体类转换成JSON
     *
     * @return 返回JSON字符串
     */
    fun <T> bean2json(t: T): String {
        val gson = Gson()
        return gson.toJson(t)
    }

    /**
     * 泛型List集合对象转换成JSON字符串
     *
     * @param list  List集合对象
     *
     * @return json  返回JSON对象
     */
    fun <T> list2json(list: List<T>): String {
        val gson = Gson()
        return gson.toJson(list)
    }

    /**
     * Map集合转换成JSONString
     *
     * @param map key为String,value为实体类<T>
     *
     * @return JSON实体类对象
    </T> */
    fun <T> map2json(map: Map<String, T>): String {
        val gson = Gson()
        return gson.toJson(map)
    }

    /**
     * Set集合转换为JSONString
     *
     * @param
     * @return 返回一个标准JSON字符串
     */
    fun <T> set2json(set: Set<T>): String {
        val gson = Gson()
        return gson.toJson(gson)
    }

    /**
     * @param json
     * @param clazz
     * @return
     */
    fun <T> jsonToArrayList(json: String, clazz: Class<T>): ArrayList<T> {
        val type = object : TypeToken<ArrayList<JsonObject>>() {

        }.getType()
        val jsonObjects = Gson().fromJson<ArrayList<JsonObject>>(json, type)

        val arrayList = ArrayList<T>()
        for (jsonObject in (jsonObjects)) {
            arrayList.add(Gson().fromJson(jsonObject, clazz))
        }
        return arrayList
    }
}