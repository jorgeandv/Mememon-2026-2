package cl.uchile.dcc
package model.units

trait Players {
  def units: List[String]
  def defeated(): Boolean
}

/*
class Player(val units: List[String]) extends Players{
  override def defeated(): Boolean = {
    It remains to be defined when it is considered defeated.
   }
}
 */
