package com.alekseysamoylov.alfa1

import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException
import java.math.BigDecimal
import javax.servlet.http.HttpServletRequest





@RestController
@RequestMapping("/analytic")
class AlfaController {
  private val log = LoggerFactory.getLogger(this::class.java)

  @GetMapping
  fun getAllData(): List<Answer> {
    log.info("get all")
    return listOf(Answer(
            "XA1VWF",
            BigDecimal("11291.42").sc(),
            mapOf(
                1 to AnalyticInfo(BigDecimal(9306.45).sc(),BigDecimal(9306.45).sc(),BigDecimal(9306.45).sc()),
                3 to AnalyticInfo(BigDecimal(1984.97).sc(),BigDecimal(1984.97).sc(),BigDecimal(1984.97).sc())
            )
        ),
        Answer(
            "XA1VWR",
            BigDecimal("11291.42").sc(),
            mapOf(
                1 to AnalyticInfo(BigDecimal(9306.45).sc(),BigDecimal(9306.45).sc(),BigDecimal(9306.45).sc()),
                3 to AnalyticInfo(BigDecimal(1984.97).sc(),BigDecimal(1984.97).sc(),BigDecimal(1984.97).sc())
            )
        ))

  }


  @GetMapping("/{userId}")
  fun getAllData(@PathVariable userId: String): Answer {
    log.info("get by user={}", userId)
    if (userId == "1") {
      throw ResponseStatusException(HttpStatus.NOT_FOUND)
    }
    return Answer(
        "XA1VWF",
        BigDecimal("11291.42").sc(),
        mapOf(
            1 to AnalyticInfo(BigDecimal(9306.45).sc(),BigDecimal(9306.45).sc(),BigDecimal(9306.45).sc()),
            3 to AnalyticInfo(BigDecimal(1984.97).sc(),BigDecimal(1984.97).sc(),BigDecimal(1984.97).sc())
        )
    )
  }

}
