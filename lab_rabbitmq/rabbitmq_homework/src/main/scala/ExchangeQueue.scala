package agh.edu.pl

import com.rabbitmq.client.{BuiltinExchangeType, Channel, Connection, ConnectionFactory, DefaultConsumer}

class ExchangeQueue(private val exchangeName: String,
                    private val exchangeType: BuiltinExchangeType,
                    private val key: String) {
  var channel: Channel = null
  private var connection: Connection = null

  initQueue()

  private def initQueue(): Unit = {
    val factory = new ConnectionFactory()
    factory.setHost(Constants.HOST)
    connection = factory.newConnection()
    channel = connection.createChannel()
    channel.exchangeDeclare(exchangeName, exchangeType)
  }

  def setUpToListen(defaultConsumer: DefaultConsumer): Unit = {
    val queueName = channel.queueDeclare.getQueue
    channel.queueBind(queueName, exchangeName, key)
    channel.basicConsume(queueName, true, defaultConsumer)
  }

  def sendMessage(message: String): Unit = channel.basicPublish(exchangeName, key, null, message.getBytes)
}
