package cl.uchile.dcc
package model.players

import model.units.UnitsCommon

trait Players {
  def units: List[UnitsCommon]
  def defeated(): Boolean
}

/*
class Player(val units: List[UnitsCommon]) extends Players{
  override def defeated(): Boolean = {
    It remains to be defined when it is considered defeated.
   }
}
 */
