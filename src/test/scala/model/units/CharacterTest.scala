package cl.uchile.dcc
package model.units

class CharacterTest extends munit.FunSuite:
  var knight: Option[Character] = None

  override def beforeEach(context: BeforeEach): Unit =
    knight = Some(new Knight("Juan", 90, 10, 55, "shotgun", List("potion")))
    
  test("classes extending Character fulfill the contract"):
    val k = knight.get
    assertEquals(k.name, "Juan")
    assertEquals(k.health, 90)
    assertEquals(k.defense, 10)
    assertEquals(k.weight, 55)
    assertEquals(k.weapon, "shotgun")
    assertEquals(k.utilities, List("potion"))

