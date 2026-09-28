package cl.uchile.dcc
package model.usable

import model.usable.*

class PotionTest extends munit.FunSuite:
  var healing: Option[Potion] = None
  var fortitude: Option[Potion] = None
  var mana: Option[Potion] = None
  var magicStrength: Option[Potion] = None

  override def beforeEach(context: BeforeEach): Unit = {
    healing = Some(new HealingPotion("Healing Potion"))
    fortitude = Some(new FortitudePotion("Fortitude Potion"))
    mana = Some(new ManaPotion("Mana Potion"))
    magicStrength = Some(new MagicStrengthPotion("Magic Strength Potion"))
  }

  test("HealingPotion fulfills the Potion contract"):
    val p = healing.get
    assertEquals(p.name, "Healing Potion")

  test("FortitudePotion fulfills the Potion contract"):
    val p = fortitude.get
    assertEquals(p.name, "Fortitude Potion")

  test("ManaPotion fulfills the Potion contract"):
    val p = mana.get
    assertEquals(p.name, "Mana Potion")

  test("MagicStrengthPotion fulfills the Potion contract"):
    val p = magicStrength.get
    assertEquals(p.name, "Magic Strength Potion")
