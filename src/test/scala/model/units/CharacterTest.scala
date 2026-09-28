package cl.uchile.dcc
package model.units

class CharacterTest extends munit.FunSuite:
  var knight: Option[Character] = None
  var archer: Option[Character] = None
  var thief: Option[Character] = None
  
  override def beforeEach(context: BeforeEach): Unit = 
    knight = Some(new Knight("Juan", 90, 10, 55, "shotgun", List("potion")))
    archer = Some(new Knight("Pedro", 92, 13, 54, "bow", List("potion")))
    thief = Some(new Knight("Diego", 80, 5, 30, "knife", List("potion")))

  test("Knight fulfills the Character contract"):
    val k = knight.get
    assertEquals(k.name, "Juan")
    assertEquals(k.health, 90)
    assertEquals(k.defense, 10)
    assertEquals(k.weight, 55)
    assertEquals(k.weapon, "shotgun")
    assertEquals(k.utilities, List("potion"))

  test("Archer fulfills the Character contract"):
    val a = archer.get
    assertEquals(a.name, "Pedro")
    assertEquals(a.health, 92)
    assertEquals(a.defense, 13)
    assertEquals(a.weight, 54)
    assertEquals(a.weapon, "bow")
    assertEquals(a.utilities, List("arrow"))

  
          
      
class MagicCharacterTest extends munit.FunSuite:
  var bwizard: Option[MagicCharacter] = None

  override def beforeEach(context: BeforeEach): Unit =
    bwizard = Some(new BlackWizard("Juan", 90, 10, 55, "shotgun", List("potion"), 5))

  test("classes extending MagicCharacter fulfill the contract") :
    val b = bwizard.get
    assertEquals(b.name, "Juan")
    assertEquals(b.health, 90)
    assertEquals(b.defense, 10)
    assertEquals(b.weight, 55)
    assertEquals(b.weapon, "shotgun")
    assertEquals(b.utilities, List("potion"))
    assertEquals(b.mana, 5)
