package cl.uchile.dcc
package model.combat

import model.units.{Archer, Character, EnemiesContract, Enemy, Knight, Thief, UnitsCommon}

import scala.collection.mutable.ArrayBuffer
import model.usable.{Sword, Weapon}

import scala.collection.mutable

class TurnSchedulerTest extends munit.FunSuite:
  var turnScheduler: Option[TurnSchedulerContract] = None

  override def beforeEach(context: BeforeEach): Unit = {
    turnScheduler = Some(new TurnScheduler)
  }

  //Test del metodo add
  //Test de las particiones
  test("add with more than one unit"):
    val knight: Character = new Knight("Juan", 10, 10, 10, None, Nil)
    val archer: Character = new Archer("Pedro", 20, 20, 20, None, Nil)
    val thief: Character = new Thief("Diego", 5, 5, 5, None, Nil)
    turnScheduler.get.add(knight)
    assertEquals(turnScheduler.get.unitsArrayBuffer, ArrayBuffer[UnitsCommon](knight))
    turnScheduler.get.add(archer)
    assertEquals(turnScheduler.get.unitsArrayBuffer, ArrayBuffer[UnitsCommon](knight, archer))
    turnScheduler.get.add(thief)
    assertEquals(turnScheduler.get.unitsArrayBuffer, ArrayBuffer[UnitsCommon](knight, archer, thief))

  //test de los casos limite
  test("add an existing unit"):
    val knight: Character = new Knight("Juan", 10, 10, 10, None, Nil)
    turnScheduler.get.add(knight)
    turnScheduler.get.add(knight)
    assertEquals(turnScheduler.get.unitsArrayBuffer, ArrayBuffer[UnitsCommon](knight))
  //----

  //Test del metodo remove -----
  //Test de las particiones
  test("remove with more than one unit"):
    val knight: Character = new Knight("Juan", 10, 10, 10, None, Nil)
    val archer: Character = new Archer("Pedro", 20, 20, 20, None, Nil)
    val thief: Character = new Thief("Diego", 5, 5, 5, None, Nil)
    turnScheduler.get.add(knight)
    turnScheduler.get.add(archer)
    turnScheduler.get.add(thief)
    turnScheduler.get.remove(archer)
    assertEquals(turnScheduler.get.unitsArrayBuffer, ArrayBuffer[UnitsCommon](knight, thief))

  //test de los casos limite
  test("remove with one unit"):
    val knight: Character = new Knight("Juan", 10, 10, 10, None, Nil)
    turnScheduler.get.add(knight)
    turnScheduler.get.remove(knight)
    assertEquals(turnScheduler.get.unitsArrayBuffer, ArrayBuffer[UnitsCommon]())

  test("remove with zero units"):
    val knight: Character = new Knight("Juan", 10, 10, 10, None, Nil)
    turnScheduler.get.remove(knight)
    assertEquals(turnScheduler.get.unitsArrayBuffer, ArrayBuffer[UnitsCommon]())

  test("remove a unit that is not in the scheduler"):
    val knight: Character = new Knight("Juan", 10, 10, 10, None, Nil)
    val archer: Character = new Archer("Pedro", 20, 20, 20, None, Nil)
    turnScheduler.get.add(knight)
    turnScheduler.get.remove(archer)
    assertEquals(turnScheduler.get.unitsArrayBuffer, ArrayBuffer[UnitsCommon](knight))
  //----

  //Test del metodo maxActionBarUnit -----
  //Test de las particiones
  test("maxActionBarUnit with an Character"):
    val sword: Weapon = new Sword("La BESTIA", 10,50, None)
    val knight: Character = new Knight("Juan", 10, 10, 10, Some(sword), Nil)
    assertEquals(turnScheduler.get.maxActionBarUnit(knight), 35.0)

  test("maxActionBarUnit with an Enemy"):
    val enemy: EnemiesContract = new Enemy("Pedro", 20, 20, 20, 50)
    assertEquals(turnScheduler.get.maxActionBarUnit(enemy), 50.0)


  //test de los casos limite
  test("maxActionBarUnit without weapon"):
    val knight: Character = new Knight("Juan", 10, 10, 10, None, Nil)
    assertEquals(turnScheduler.get.maxActionBarUnit(knight), 10.0)
  //----


  //Test del metodo reset -----
  //Test de las particiones
  test("reset sets the action bar of a unit back to 0"):
    val knight: Character = new Knight("Juan", 10, 10, 10, None, Nil)
    turnScheduler.get.add(knight)
    turnScheduler.get.actionBarsMap(knight) = 7
    turnScheduler.get.reset(knight)
    assertEquals(turnScheduler.get.actionBarsMap(knight), 0)

  test("reset does not change the action bar of other units"):
    val knight: Character = new Knight("Juan", 10, 10, 10, None, Nil)
    val enemy: EnemiesContract = new Enemy("Pedro", 20, 20, 20, 50)
    turnScheduler.get.add(knight)
    turnScheduler.get.add(enemy)
    turnScheduler.get.actionBarsMap(knight) = 7
    turnScheduler.get.actionBarsMap(enemy) = 12
    turnScheduler.get.reset(knight)
    assertEquals(turnScheduler.get.actionBarsMap, mutable.Map[UnitsCommon, Int](knight -> 0, enemy -> 12))

  //test de los casos limite
  test("reset a unit whose action bar is already 0 keeps it in 0"):
    val knight: Character = new Knight("Juan", 10, 10, 10, None, Nil)
    turnScheduler.get.add(knight)
    turnScheduler.get.reset(knight)
    assertEquals(turnScheduler.get.actionBarsMap(knight), 0)

  test("reset a unit that is not in the scheduler does not add it"):
    val knight: Character = new Knight("Juan", 10, 10, 10, None, Nil)
    turnScheduler.get.reset(knight)
    assertEquals(turnScheduler.get.actionBarsMap, mutable.Map[UnitsCommon, Int]())

  test("reset a unit that is not in the scheduler does not change the others"):
    val knight: Character = new Knight("Juan", 10, 10, 10, None, Nil)
    val enemy: EnemiesContract = new Enemy("Pedro", 20, 20, 20, 50)
    turnScheduler.get.add(knight)
    turnScheduler.get.actionBarsMap(knight) = 7
    turnScheduler.get.reset(enemy)
    assertEquals(turnScheduler.get.actionBarsMap, mutable.Map[UnitsCommon, Int](knight -> 7))
  //----


  //Test del metodo increaseActionBars -----
  //test de los casos limite
  test("increaseActionBars increases the action bar of all units by k"):
    val knight: Character = new Knight("Juan", 10, 10, 10, None, Nil)
    val enemy: EnemiesContract = new Enemy("Pedro", 20, 20, 20, 50)
    turnScheduler.get.add(knight)
    turnScheduler.get.add(enemy)
    turnScheduler.get.increaseActionBars(5)
    assertEquals(turnScheduler.get.actionBarsMap, mutable.Map[UnitsCommon, Int](knight -> 5, enemy -> 5))

  test("increaseActionBars adds to the current value of each bar"):
    val knight: Character = new Knight("Juan", 10, 10, 10, None, Nil)
    val enemy: EnemiesContract = new Enemy("Pedro", 20, 20, 20, 50)
    turnScheduler.get.add(knight)
    turnScheduler.get.add(enemy)
    turnScheduler.get.actionBarsMap(knight) = 7
    turnScheduler.get.actionBarsMap(enemy) = 12
    turnScheduler.get.increaseActionBars(3)
    assertEquals(turnScheduler.get.actionBarsMap, mutable.Map[UnitsCommon, Int](knight -> 10, enemy -> 15))


  //test de los casos limite
  test("increaseActionBars with k = 0 does not change the bars"):
    val knight: Character = new Knight("Juan", 10, 10, 10, None, Nil)
    turnScheduler.get.add(knight)
    turnScheduler.get.actionBarsMap(knight) = 7
    turnScheduler.get.increaseActionBars(0)
    assertEquals(turnScheduler.get.actionBarsMap(knight), 7)

  test("increaseActionBars with negative k does not change the bars"):
    val knight: Character = new Knight("Juan", 10, 10, 10, None, Nil)
    turnScheduler.get.add(knight)
    turnScheduler.get.actionBarsMap(knight) = 7
    turnScheduler.get.increaseActionBars(-1)
    assertEquals(turnScheduler.get.actionBarsMap(knight), 7)

  test("increaseActionBars with no units does not add anything"):
    turnScheduler.get.increaseActionBars(5)
    assertEquals(turnScheduler.get.actionBarsMap, mutable.Map[UnitsCommon, Int]())


  //Test del metodo statusActionBar -----
  //test de las particiones
  test("statusActionBar is false when the bar is below the max"):
    val knight: Character = new Knight("Juan", 10, 10, 10, None, Nil)
    turnScheduler.get.add(knight)
    turnScheduler.get.actionBarsMap(knight) = 0
    assertEquals(turnScheduler.get.statusActionBar(knight), false)

  test("statusActionBar is true when the bar equals the max"):
    val knight: Character = new Knight("Juan", 10, 10, 10, None, Nil)
    turnScheduler.get.add(knight)
    val maximo = turnScheduler.get.maxActionBarUnit(knight)
    turnScheduler.get.actionBarsMap(knight) = maximo.toInt
    assertEquals(turnScheduler.get.statusActionBar(knight), true)

  test("statusActionBar is true when the bar exceeds the max"):
    val knight: Character = new Knight("Juan", 10, 10, 10, None, Nil)
    turnScheduler.get.add(knight)
    val maximo = turnScheduler.get.maxActionBarUnit(knight)
    turnScheduler.get.actionBarsMap(knight) = maximo.toInt + 5
    assertEquals(turnScheduler.get.statusActionBar(knight), true)

  //test de los casos limites
  test("statusActionBar is false when the bar is one below the max"):
    val knight: Character = new Knight("Juan", 10, 10, 10, None, Nil)
    turnScheduler.get.add(knight)
    val maximo = turnScheduler.get.maxActionBarUnit(knight)
    turnScheduler.get.actionBarsMap(knight) = maximo.toInt - 1
    assertEquals(turnScheduler.get.statusActionBar(knight), false)

  test("statusActionBar is false for a unit not in the scheduler"):
    val knight: Character = new Knight("Juan", 10, 10, 10, None, Nil)
    assertEquals(turnScheduler.get.statusActionBar(knight), false)
  //----

  //Test del metodo completedUnits -----
  //test de las particiones
  test("completedUnits is empty when there are no units"):
    assertEquals(turnScheduler.get.completedUnits(), ArrayBuffer[UnitsCommon]())

  test("completedUnits is empty when no unit completed its bar"):
    val knight: Character = new Knight("Juan", 10, 10, 10, None, Nil)
    val enemy: EnemiesContract = new Enemy("Pedro", 20, 20, 20, 50)
    turnScheduler.get.add(knight)
    turnScheduler.get.add(enemy)
    turnScheduler.get.actionBarsMap(knight) = 0
    turnScheduler.get.actionBarsMap(enemy) = 0
    assertEquals(turnScheduler.get.completedUnits(), ArrayBuffer[UnitsCommon]())

  test("completedUnits returns only the unit that completed its bar"):
    val knight: Character = new Knight("Juan", 10, 10, 10, None, Nil)
    val enemy: EnemiesContract = new Enemy("Pedro", 20, 20, 20, 50)
    turnScheduler.get.add(knight)
    turnScheduler.get.add(enemy)
    turnScheduler.get.actionBarsMap(knight) = turnScheduler.get.maxActionBarUnit(knight).toInt + 3
    turnScheduler.get.actionBarsMap(enemy) = 0
    assertEquals(turnScheduler.get.completedUnits(), ArrayBuffer[UnitsCommon](knight))

  test("completedUnits includes a unit with a excedents of exactly 0"):
    val knight: Character = new Knight("Juan", 10, 10, 10, None, Nil)
    turnScheduler.get.add(knight)
    turnScheduler.get.actionBarsMap(knight) = turnScheduler.get.maxActionBarUnit(knight).toInt
    assertEquals(turnScheduler.get.completedUnits(), ArrayBuffer[UnitsCommon](knight))

  test("completedUnits sorts from highest to lowest excedent"):
    val knight: Character = new Knight("Juan", 10, 10, 10, None, Nil)
    val enemy: EnemiesContract = new Enemy("Pedro", 20, 20, 20, 50)
    turnScheduler.get.add(knight)
    turnScheduler.get.add(enemy)
    turnScheduler.get.actionBarsMap(knight) = turnScheduler.get.maxActionBarUnit(knight).toInt + 2
    turnScheduler.get.actionBarsMap(enemy) = turnScheduler.get.maxActionBarUnit(enemy).toInt + 9
    assertEquals(turnScheduler.get.completedUnits(), ArrayBuffer[UnitsCommon](enemy, knight))
  //----

  //Tests del metodo currentTurn -----
  test("currentTurn is None when no unit completed its bar"):
    val knight: Character = new Knight("Juan", 10, 10, 10, None, Nil)
    turnScheduler.get.add(knight)
    assertEquals(turnScheduler.get.currentTurn(), None)

  test("currentTurn is the only unit that completed its bar"):
    val knight: Character = new Knight("Juan", 10, 10, 10, None, Nil)
    val enemy: EnemiesContract = new Enemy("Pedro", 20, 20, 20, 50)
    turnScheduler.get.add(knight)
    turnScheduler.get.add(enemy)
    turnScheduler.get.actionBarsMap(knight) = turnScheduler.get.maxActionBarUnit(knight).toInt
    assertEquals(turnScheduler.get.currentTurn(), Some(knight))

  test("currentTurn is None when there are no units"):
    assertEquals(turnScheduler.get.currentTurn(), None)



