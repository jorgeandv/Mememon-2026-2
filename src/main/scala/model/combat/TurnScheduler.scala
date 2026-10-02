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
  def actionBarsMap: mutable.Map[UnitsCommon, Int]
  def reset(unit: UnitsCommon): mutable.Map[UnitsCommon, Int]
  def increaseActionBars(k: Int): mutable.Map[UnitsCommon, Int]
  def statusActionBar(unit: UnitsCommon): Boolean
  def completedUnits(): ArrayBuffer[UnitsCommon]
  def currentTurn(): Option[UnitsCommon]
}

class TurnScheduler extends TurnSchedulerContract {
  val units: ArrayBuffer[UnitsCommon] = ArrayBuffer[UnitsCommon]()
  //Creo el Map que tendra los pares unidad - barra
  val actionBars: mutable.Map[UnitsCommon, Int] = mutable.Map[UnitsCommon, Int]()
  override def unitsArrayBuffer: ArrayBuffer[UnitsCommon] = units
  override def actionBarsMap: mutable.Map[UnitsCommon, Int] = actionBars

  override def add(unit: UnitsCommon): ArrayBuffer[UnitsCommon] = {
    //Evita duplicados
    if (!units.contains(unit)) then {
      units += unit
      actionBars(unit) = 0
    }
    units
  }

  override def remove(unit: UnitsCommon): ArrayBuffer[UnitsCommon] = {
    //Evita elimianr cuando no hay nada
    units-=unit
    actionBars-=unit
    units
  }

  override def maxActionBarUnit(unit: UnitsCommon): Double = {
    unit match {
      //sin arma, el peso del arma es 0
      case c: Character => c.weight + 0.5*c.weapon.map(_.weight).getOrElse(0)
      case c: EnemiesContract => c.weight
    }
  }

  override def reset(unit: UnitsCommon): mutable.Map[UnitsCommon, Int] = {
    //solo reinicia si la unidad está en el programador
    if (actionBars.contains(unit)) then actionBars(unit) = 0
    actionBars
  }

  override def increaseActionBars(k: Int): mutable.Map[UnitsCommon, Int] = {
    if (k >= 0) then
      for ((key, value) <- actionBars) {
        actionBars(key) = value + k
      }
    actionBars
  }

  override def statusActionBar(unit: UnitsCommon): Boolean = {
    if actionBars.contains(unit) then
      val maximo: Double = maxActionBarUnit(unit)
      if actionBars(unit) >= maximo then true
      else false
    else false
  }

  // Calcula cuánto se pasó la unidad del máximo de su barra (barra actual - máximo)
  def excedente(unit: UnitsCommon): Double = {
    actionBars(unit) - maxActionBarUnit(unit)
  }

  override def completedUnits(): ArrayBuffer[UnitsCommon] = {
    val resultado = ArrayBuffer[UnitsCommon]()

    //Recorre todas las unidades registradas en el scheduler
    for unit <- actionBars.keys do
      //Interesan las que completaron su barra de acción
      if statusActionBar(unit) then
        //Posición donde se insertará la unidad; parte en 0 (el inicio de la lista)
        var posicion = 0

        // Avanza mientras:
        //No se llegue al final de la lista, y
        //La unidad que ya está en esa posición tenga un excedente mayor o igual
        //al de la nueva (o sea, la nueva todavía no le gana)
        while (posicion < resultado.length && excedente(resultado(posicion)) >= excedente(unit)) {
          posicion+=1
        }

        // Cuando el while termina, "posicion" es el lugar correcto:
        // inserta la unidad ahí, desplazando las demás hacia atrás
        resultado.insert(posicion, unit)

    // Devuelve la lista ya ordenada de mayor a menor excedente
    resultado
  }

  override def currentTurn(): Option[UnitsCommon] = {
    val completadas = completedUnits()
    if completadas.isEmpty then None
    else Some(completadas(0))
  }
}


