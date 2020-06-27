package com.alekseysamoylov.alfa1

import java.math.BigDecimal
import java.math.RoundingMode


data class Answer(
     val userId: String,
     val totalSum: BigDecimal,
     val analyticInfo: Map<Int, AnalyticInfo>
 )

data class AnalyticInfo(
    val min: BigDecimal,
    val max: BigDecimal,
    val sum: BigDecimal
)
data class GetAllResponse(
    val list: List<Answer>
)

fun BigDecimal.sc(): BigDecimal {
  return this.setScale(2, RoundingMode.HALF_UP)
}

