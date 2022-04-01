package agh.edu.pl

case object Constants {
  final val HOST = "localhost"
  final val MESSAGE_SPLITTER = " "
  final val MAX_SAME_ITEM_AMOUNT = 4

  final val ORDER_EXCHANGE = "order_exchange"
  final val CONFIRM_ORDER_EXCHANGE = "confirm_order_exchange"
  final val ADMIN_EXCHANGE = "admin_exchange"

  final val ORDER_QUEUE = "queue_order"
  
  final val ALL_KEY = "*"
  final val ORDER_KEY = "order"
  final val ADMIN_TO_TEAM_KEY = "admin.no.team"
  final val ADMIN_TO_SUPPLIER_KEY = "admin.supplier.no"
  final val ADMIN_TO_EVERYONE_KEY = "admin.supplier.team"
  final val TEAM_FROM_ADMIN_KEY = "admin.*.team"
  final val SUPPLIER_FROM_ADMIN_KEY = "admin.supplier.*"
}
