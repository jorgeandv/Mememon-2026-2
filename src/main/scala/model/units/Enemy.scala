package cl.uchile.dcc
package model.units

trait EnemiesContract {
  def name: String
  def health: Int
  def attack: Int
  def defense: Int
  def weight: Int
}

class Enemy(val name: String, val health: Int, val attack: Int, val defense: Int, val weight: Int) extends EnemiesContract 
