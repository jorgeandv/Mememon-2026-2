package cl.uchile.dcc
package model.actions

import model.usable.usableCommon

trait ActionContract {
  def name: String
}

trait ActionWithUsable extends ActionContract {
  def objects: List[usableCommon]
}
