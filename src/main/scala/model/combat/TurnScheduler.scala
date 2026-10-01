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
}

class TurnScheduler extends TurnSchedulerContract {
  val units: ArrayBuffer[UnitsCommon] = ArrayBuffer[UnitsCommon]()
  //Creo el Map que tendra los pares unidad - barra
  val actionBarsMap: mutable.Map[UnitsCommon, Int] = mutable.Map[UnitsCommon, Int]()
  override def unitsArrayBuffer: ArrayBuffer[UnitsCommon] = units

  override def add(unit: UnitsCommon): ArrayBuffer[UnitsCommon] = {
    //Evita duplicados
    if (!units.contains(unit)) then {
      units += unit
      actionBarsMap(unit) = 0
    }
    units
  }

  override def remove(unit: UnitsCommon): ArrayBuffer[UnitsCommon] = {
    //Evita elimianr cuando no hay nada
    units-=unit
    actionBarsMap-=unit
    units
  }

  override def maxActionBarUnit(unit: UnitsCommon): Double = {
    unit match {
      //sin arma, el peso del arma es 0
      case c: Character => c.weight + 0.5*c.weapon.map(_.weight).getOrElse(0)
      case c: EnemiesContract => c.weight
    }
  }
}