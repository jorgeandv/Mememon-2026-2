package cl.uchile.dcc
package model.combat

import model.units.UnitsCommon
import scala.collection.mutable.ArrayBuffer

trait TurnSchedulerContract {
  def unitsArrayBuffer: ArrayBuffer[UnitsCommon]
  def add(unit: UnitsCommon): ArrayBuffer[UnitsCommon]
  def remove(unit: UnitsCommon): ArrayBuffer[UnitsCommon]
}

class TurnScheduler extends TurnSchedulerContract {
  val units: ArrayBuffer[UnitsCommon] = ArrayBuffer[UnitsCommon]()
  override def unitsArrayBuffer: ArrayBuffer[UnitsCommon] = units

  override def add(unit: UnitsCommon): ArrayBuffer[UnitsCommon] = {
    //Evita duplicados
    if (!units.contains(unit)) then units += unit
    units
  }

  override def remove(unit: UnitsCommon): ArrayBuffer[UnitsCommon] = {
    //Evita elimianr cuando no hay nada
    units-=unit
    units
  }
}