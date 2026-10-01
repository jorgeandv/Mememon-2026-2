package cl.uchile.dcc
package model.combat

import model.units.UnitsCommon
import scala.collection.mutable.ArrayBuffer

trait TurnSchedulerContract {
  def unitsArrayBuffer: ArrayBuffer[UnitsCommon]
  def add(unit: UnitsCommon): ArrayBuffer[UnitsCommon]
}

class TurnScheduler extends TurnSchedulerContract {
  val units: ArrayBuffer[UnitsCommon] = ArrayBuffer[UnitsCommon]()
  override def unitsArrayBuffer: ArrayBuffer[UnitsCommon] = units

  override def add(unit: UnitsCommon): ArrayBuffer[UnitsCommon] = {
    //evita duplicados
    if (!units.contains(unit)) units += unit
    units
  }
}