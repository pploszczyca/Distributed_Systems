package agh.edu.pl

import queue_services.ChannelService

import com.rabbitmq.client.{AMQP, DefaultConsumer, Envelope}

import java.nio.charset.StandardCharsets

object DefaultPrinterConsumer {
  def setPrinterToChannelService(channelService: ChannelService): Unit = {
    val defaultConsumer = new DefaultConsumer(channelService.channel) {
      override def handleDelivery(consumerTag: String, envelope: Envelope, properties: AMQP.BasicProperties, body: Array[Byte]): Unit =
        println(String(body, StandardCharsets.UTF_8))
    }

    channelService.setUpToListen(defaultConsumer)
  }
}
