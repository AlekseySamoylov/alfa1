package com.alekseysamoylov.alfa1

import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.common.serialization.StringDeserializer
import org.slf4j.LoggerFactory
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.kafka.annotation.EnableKafka
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory
import org.springframework.kafka.core.ConsumerFactory
import org.springframework.kafka.core.DefaultKafkaConsumerFactory
import org.springframework.kafka.support.serializer.JsonDeserializer
import org.springframework.stereotype.Component


@EnableKafka
@SpringBootApplication
class Alfa1Application {
  private val log = LoggerFactory.getLogger(this::class.java)
  private val bootstrapAddress: String = "130.193.49.95:29092"

  @Bean
  fun consumerFactory(): ConsumerFactory<String?, String?> {
    val config: MutableMap<String, Any> = HashMap()
    config[ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG] = "130.193.49.95:29092"
    config[ConsumerConfig.GROUP_ID_CONFIG] = "group_id"
    config[ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG] = StringDeserializer::class.java
    config[ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG] = StringDeserializer::class.java
    return DefaultKafkaConsumerFactory(config)
  }


  @Bean
  fun kafkaListenerContainerFactory(): ConcurrentKafkaListenerContainerFactory<String, String> {
    val factory: ConcurrentKafkaListenerContainerFactory<String, String> = ConcurrentKafkaListenerContainerFactory()
    factory.consumerFactory = consumerFactory()
    return factory
  }


  @Bean
  fun userConsumerFactory(): ConsumerFactory<String, String> {
    val config: MutableMap<String, Any> = HashMap()
    config[ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG] = "130.193.49.95:29092"
    config[ConsumerConfig.GROUP_ID_CONFIG] = "group_string"
    config[ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG] = StringDeserializer::class.java
    config[ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG] = StringDeserializer::class.java
    return DefaultKafkaConsumerFactory(config, StringDeserializer(), StringDeserializer())
  }

  @Bean
  fun userKafkaListenerFactory(): ConcurrentKafkaListenerContainerFactory<String, String> {
    val factory: ConcurrentKafkaListenerContainerFactory<String, String> = ConcurrentKafkaListenerContainerFactory<String, String>()
    factory.consumerFactory = userConsumerFactory()
    return factory
  }

}

fun main(args: Array<String>) {
  runApplication<Alfa1Application>(*args)
}

@Component
class Service {
  private val log = LoggerFactory.getLogger(this::class.java)
  @KafkaListener(topics = ["RAW_PAYMENTS"], groupId = "foo5")
  fun listen(message: String) {
    log.info("Received Messasge in group foo: {}", message)
  }
}

