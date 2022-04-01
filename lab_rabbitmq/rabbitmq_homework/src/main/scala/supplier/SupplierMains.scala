package agh.edu.pl
package supplier

import models.Equipment

object SupplierMain {
  def main(args: Array[String]): Unit = {
    Supplier("Supplier1", List(Equipment.Oxygen, Equipment.Backpack))
  }
}

object SecondSupplierMain {
  def main(args: Array[String]): Unit = {
    Supplier("Supplier2", List(Equipment.Oxygen, Equipment.Boots))
  }
}
