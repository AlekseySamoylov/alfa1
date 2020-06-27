package com.alekseysamoylov.alfa1

import com.google.gson.Gson
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import java.math.BigDecimal


@Component
class DataService {
  private val resultMap = mutableMapOf<String, Answer>()
  private val log = LoggerFactory.getLogger(this::class.java)

  @Synchronized
  fun get(userId: String): Answer? {
    return resultMap[userId]
  }

  @Synchronized
  fun getAll(): List<Answer> {
    return resultMap.values.toList()
  }

  @Synchronized
  fun consumeRaw(rawData1: String) {
    val data = parseData(rawData1) ?: return
    val userId = data.first
    val category = data.second
    val value = data.third
    val existedData = resultMap[userId]
    if (existedData != null) {
      val existedCategory = existedData.analyticInfo[category]
      val categoryAnalyticInfoToSet = if (existedCategory != null) {
        AnalyticInfo(
            if (value.sc().compareTo(existedCategory.min.sc()) == -1) {
              value.sc()
            } else {
              existedCategory.min.sc()
            },
            if (value.sc().compareTo(existedCategory.max.sc()) == 1) {
              value.sc()
            } else {
              existedCategory.max.sc()
            },
            existedCategory.sum.add(value).sc()
        )
      } else {
        AnalyticInfo(value, value, value)
      }
      val mapToSet = existedData.analyticInfo.toMutableMap()
      mapToSet[category] = categoryAnalyticInfoToSet
      val updatedData = Answer(
          userId,
          existedData.totalSum.add(value).sc(),
          mapToSet
      )
      resultMap[userId] = updatedData
    } else {
      val answer = Answer(userId, value, mutableMapOf(
          category to AnalyticInfo(value, value, value)
      ))
      resultMap[userId] = answer
    }
  }

  private val gson = Gson()
  private val USER_ID_KEY = "userId"
  private val CATEGORY_KEY = "categoryId"
  private val AMOUNT_KEY = "amount"
  fun parseData(rawData1: String): Triple<String, Int, BigDecimal>? {
    return try {
      var objectMap = mutableMapOf<String, Any>()
      objectMap = gson.fromJson(rawData1, objectMap.javaClass)
      Triple(objectMap[USER_ID_KEY]!! as String, (objectMap[CATEGORY_KEY]!! as Double).toInt(), BigDecimal.valueOf(objectMap[AMOUNT_KEY] as Double).sc())
    } catch (ex: Exception) {
      log.warn("Parsing error $rawData1", ex)
      null
    }
  }


}
