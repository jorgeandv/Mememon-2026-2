package cl.uchile.dcc
package model.units

trait Character {
  def name: String
  def health: Int
  def defense: Int
  def weight: Int
  def weapon: String
  def utilities: List[String]
}

