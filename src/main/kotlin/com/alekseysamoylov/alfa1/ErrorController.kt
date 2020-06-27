package com.alekseysamoylov.alfa1

import org.springframework.boot.autoconfigure.web.servlet.error.AbstractErrorController
import org.springframework.boot.web.servlet.error.ErrorAttributes
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.servlet.http.HttpServletRequest


@RestController
class ErrorController(errorAttributes: ErrorAttributes) : AbstractErrorController(errorAttributes) {
  override fun getErrorPath(): String {
    return "/error"
  }

  @RequestMapping(path = ["/error"])
  fun handleError(request: HttpServletRequest): ResponseEntity<*>? {
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(mapOf("status" to "user not found"))
  }
}
