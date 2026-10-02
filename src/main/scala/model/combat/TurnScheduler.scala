package cl.uchile.dcc
package model.combat

import scala.collection.mutable.ArrayBuffer
import scala.collection.mutable
import model.units.{UnitsCommon, Character, EnemiesContract, Enemy}
import model.usable.Weapon

trait TurnSchedulerContract {
  def unitsArrayBuffer: ArrayBuffer[UnitsCommon]
  def add(unit: UnitsCommon): ArrayBuffer[UnitsCommon]
  def remove(unit: UnitsCommon): ArrayBuffer[UnitsCommon]
  def maxActionBarUnit(unit: UnitsCommon): Double
  def actionBarsMap: mutable.Map[UnitsCommon, Int]
  def reset(unit: UnitsCommon): mutable.Map[UnitsCommon, Int]
  def increaseActionBars(k: Int): mutable.Map[UnitsCommon, Int]
  def statusActionBar(unit: UnitsCommon): Boolean
  def completedUnits(): ArrayBuffer[UnitsCommon]
  def currentTurn(): Option[UnitsCommon]
}

class TurnScheduler extends TurnSchedulerContract {
  val units: ArrayBuffer[UnitsCommon] = ArrayBuffer[UnitsCommon]()
  val actionBars: mutable.Map[UnitsCommon, Int] = mutable.Map[UnitsCommon, Int]()
  override def unitsArrayBuffer: ArrayBuffer[UnitsCommon] = units
  override def actionBarsMap: mutable.Map[UnitsCommon, Int] = actionBars

  override def add(unit: UnitsCommon): ArrayBuffer[UnitsCommon] = {
    if (!units.contains(unit)) then {
      units += unit
      actionBars(unit) = 0
    }
    units
  }

  override def remove(unit: UnitsCommon): ArrayBuffer[UnitsCommon] = {
    units-=unit
    actionBars-=unit
    units
  }

  override def maxActionBarUnit(unit: UnitsCommon): Double = {
    unit match {
      case c: Character => c.weight + 0.5*c.weapon.map(_.weight).getOrElse(0)
      case c: EnemiesContract => c.weight
    }
  }

  override def reset(unit: UnitsCommon): mutable.Map[UnitsCommon, Int] = {
    if (actionBars.contains(unit)) then actionBars(unit) = 0
    actionBars
  }

  override def increaseActionBars(k: Int): mutable.Map[UnitsCommon, Int] = {
    if (k >= 0) then
      for ((key, value) <- actionBars) {
        actionBars(key) = value + k
      }
    actionBars
  }

  override def statusActionBar(unit: UnitsCommon): Boolean = {
    if actionBars.contains(unit) then
      val maximo: Double = maxActionBarUnit(unit)
      if actionBars(unit) >= maximo then true
      else false
    else false
  }

  def excedent(unit: UnitsCommon): Double = {
    actionBars(unit) - maxActionBarUnit(unit)
  }

  override def completedUnits(): ArrayBuffer[UnitsCommon] = {
    val result = ArrayBuffer[UnitsCommon]()

    for unit <- actionBars.keys do
      if statusActionBar(unit) then
        var position = 0

        //Avanza mientras
        //No se llegue al final de la lista, y
        //La unidad que ya está en esa posición tenga un excedente mayor o igual
        //al de la nueva (o sea, la nueva todavía no le gana)
        while (position < result.length && excedent(result(position)) >= excedent(unit)) {
          position+=1
        }

        result.insert(position, unit)

    result
  }

  override def currentTurn(): Option[UnitsCommon] = {
    val completed = completedUnits()
    if completed.isEmpty then None
    else Some(completed(0))
  }
}


