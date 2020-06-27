package com.alekseysamoylov.alfa1

import org.springframework.stereotype.Component


@Component
class DataService {
  fun get(userId: String): Answer {
    TODO("Not yet implemented")
  }

  fun getAll(): List<Answer> {
    TODO("Not yet implemented")
  }

  fun consumeRaw(rawData1: String) {
    TODO("Not yet implemented")
  }

  private val rawMessages = mutableListOf<String>("{\"ref\":\"U031706200001019\",\"categoryId\":3,\"userId\":\"XA1VWF\",\"recipientId\":\"XA6F9K\",\"desc\":\"Платежное поручение №1019\",\"amount\":1984.97}")


}
