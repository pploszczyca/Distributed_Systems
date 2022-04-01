package agh.edu.pl
package team

import models.Equipment
import scala.io.StdIn.readLine

object TeamMain {
  def main(args: Array[String]): Unit = {
    val team = Team("TeamOne")
    val neededEquipment = List(Equipment.Oxygen, Equipment.Oxygen, Equipment.Boots, Equipment.Boots, Equipment.Boots, Equipment.Boots, Equipment.Backpack, Equipment.Backpack, Equipment.Oxygen)

    for
      equipment <- neededEquipment
    do
      team.sendOrder(equipment)
  }
}

object TeamToWriteMain {
  def main(args: Array[String]): Unit = {
    val team = Team("TeamSecond")

    while
      true
    do
      team.sendOrder(Equipment.Oxygen.getByName(readLine()))
  }
}
