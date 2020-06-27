package com.alekseysamoylov.alfa1

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.math.BigDecimal


internal class DataServiceTest {

  @Test
  fun shouldParseDataStoreAndSendForAll() {
    // Given
    val service = DataService()
    val rawData1 = """{"ref":"U031706200001019","categoryId":3,"userId":"XA1VWF","recipientId":"XA6F9K","desc":"Платежное поручение №1019","amount":1984.97}"""
    val rawData2 = """{"ref":"U031706200001017","categoryId":1,"userId":"XA1VWF","recipientId":"XA6BFO","desc":"Платежное поручение №1017","amount":9306.45}"""
    val rawData3 = """{"ref":"U031706200001018","categoryId":2,"userId":"XA3SZV","recipientId":"XA6F9K","desc":"Платежное поручение №1018","amount":9722.64}"""

    val expectedMessage = ""

    val result = service.getAll()

  }

  @Test
  fun shouldParseDataStoreAndSendForOne() {
    // Given
    val service = DataService()
    val userId = "XA1VWF"
    val rawData1 = """{"ref":"U031706200001019","categoryId":3,"userId":"XA1VWF","recipientId":"XA6F9K","desc":"Платежное поручение №1019","amount":1984.97}"""
    val rawData2 = """{"ref":"U031706200001017","categoryId":1,"userId":"XA1VWF","recipientId":"XA6BFO","desc":"Платежное поручение №1017","amount":9306.45}"""
    val rawData3 = """{"ref":"U031706200001018","categoryId":2,"userId":"XA3SZV","recipientId":"XA6F9K","desc":"Платежное поручение №1018","amount":9722.64}"""

    val expectedMessage = Answer(
        "XA1VWF",
        BigDecimal("11291.42"),
        mapOf(
            1 to AnalyticInfo(BigDecimal(9306.45),BigDecimal(9306.45),BigDecimal(9306.45)),
            3 to AnalyticInfo(BigDecimal(1984.97),BigDecimal(1984.97),BigDecimal(1984.97))
        )
    )
    service.consumeRaw(rawData1)
    service.consumeRaw(rawData2)
    service.consumeRaw(rawData3)

    // When
    val result = service.get(userId)

    // Then
    assertThat(result).isEqualTo(expectedMessage)
  }
}
