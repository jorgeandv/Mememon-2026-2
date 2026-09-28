package cl.uchile.dcc
package model.Panel

import model.units.UnitsCommon

trait PanelContract {
  def x: Int
  def y: Int
  def units: List[UnitsCommon]
  def nearbyPanels(): List[Panel]
}

class Panel(val x: Int, val y: Int, val units: List[UnitsCommon]) {
  override def nearbyPanels(): List[Panel] = {

  }
}


