package agh.edu.pl
package queue_services

import com.rabbitmq.client.{Channel, Connection, ConnectionFactory, DefaultConsumer}

abstract class ChannelService:
  var channel: Channel = null
  protected var connection: Connection = null

  initQueue()

  protected def initQueue(): Unit = {
    val factory = new ConnectionFactory()
    factory.setHost(Constants.HOST)
    connection = factory.newConnection()
    channel = connection.createChannel()
  }

  def sendMessage(message: String): Unit

  def setUpToListen(defaultConsumer: DefaultConsumer): Unit


