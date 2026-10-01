package cl.uchile.dcc
package model.combat

import model.units.UnitsCommon
import scala.collection.mutable.ArrayBuffer

trait TurnSchedulerContract {
  def unitsArrayBuffer: ArrayBuffer[UnitsCommon]
  def add(unit: UnitsCommon): ArrayBuffer[UnitsCommon]
}

class TurnScheduler extends TurnSchedulerContract {
  override def unitsArrayBuffer: ArrayBuffer[UnitsCommon] = ArrayBuffer[UnitsCommon]()
  
  override def add(unit: UnitsCommon): ArrayBuffer[UnitsCommon] = {
    this.unitsArrayBuffer+=unit
  }
}