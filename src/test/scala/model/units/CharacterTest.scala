package cl.uchile.dcc
package model.units

class CharacterTest extends munit.FunSuite:
  var knight: Option[Character] = None
  var archer: Option[Character] = None
  var thief: Option[Character] = None

  override def beforeEach(context: BeforeEach): Unit = {
    knight = Some(new Knight("Juan", 90, 10, 55, None, List("potion")))
    archer = Some(new Archer("Pedro", 92, 13, 54, None, List("arrow")))
    thief = Some(new Thief("Diego", 80, 5, 30, None, List("potion")))
  }

  test("Knight fulfills the Character contract"):
    val k = knight.get
    assertEquals(k.name, "Juan")
    assertEquals(k.health, 90)
    assertEquals(k.defense, 10)
    assertEquals(k.weight, 55)
    assertEquals(k.weapon, None)
    assertEquals(k.utilities, List("potion"))

  test("Archer fulfills the Character contract"):
    val a = archer.get
    assertEquals(a.name, "Pedro")
    assertEquals(a.health, 92)
    assertEquals(a.defense, 13)
    assertEquals(a.weight, 54)
    assertEquals(a.weapon, None)
    assertEquals(a.utilities, List("arrow"))

  test("Thief fulfills the Character contract"):
    val t = thief.get
    assertEquals(t.name, "Diego")
    assertEquals(t.health, 80)
    assertEquals(t.defense, 5)
    assertEquals(t.weight, 30)
    assertEquals(t.weapon, None)
    assertEquals(t.utilities, List("potion"))


class MagicCharacterTest extends munit.FunSuite:
  var bwizard: Option[MagicCharacter] = None
  var wwizard: Option[MagicCharacter] = None

  override def beforeEach(context: BeforeEach): Unit = {
    bwizard = Some(new BlackWizard("Juan", 90, 10, 55, None, List("potion"), 5))
    wwizard = Some(new WhiteWizard("Matias", 80, 5, 60, None, List("potion"), 6))
  }

  test("BlackWizard fulfills the MagicCharacter contract"):
    val b = bwizard.get
    assertEquals(b.name, "Juan")
    assertEquals(b.health, 90)
    assertEquals(b.defense, 10)
    assertEquals(b.weight, 55)
    assertEquals(b.weapon, None)
    assertEquals(b.utilities, List("potion"))
    assertEquals(b.mana, 5)

  test("WhiteWizard fulfills the MagicCharacter contract"):
    val w = wwizard.get
    assertEquals(w.name, "Matias")
    assertEquals(w.health, 80)
    assertEquals(w.defense, 5)
    assertEquals(w.weight, 60)
    assertEquals(w.weapon, None)
    assertEquals(w.utilities, List("potion"))
    assertEquals(w.mana, 6)










