package cl.uchile.dcc
package model.units

class WeaponTest extends munit.FunSuite:
  var knight: Option[Character] = None
  var sword: Option[Weapon] = None
  var dagger: Option[Weapon] = None
  var bow: Option[Weapon] = None

  override def beforeEach(context: BeforeEach): Unit = {
    sword = Some(new Sword("Excalibur", 30, 8, None))
    knight = Some(new Knight("Juan", 90, 10, 55, sword, List("potion")))
    dagger = Some(new Dagger("Shiv", 12, 2, knight))
    bow = Some(new Bow("Longbow", 20, 4, knight))
  }

  test("Sword fulfills the Weapon contract"):
    val s = sword.get
    assertEquals(s.name, "Excalibur")
    assertEquals(s.attack, 30)
    assertEquals(s.weight, 8)
    assertEquals(s.owner, None)

  test("Dagger fulfills the Weapon contract"):
    val d = dagger.get
    assertEquals(d.name, "Shiv")
    assertEquals(d.attack, 12)
    assertEquals(d.weight, 2)
    assertEquals(d.owner, knight)

  test("Bow fulfills the Weapon contract"):
    val b = bow.get
    assertEquals(b.name, "Longbow")
    assertEquals(b.attack, 20)
    assertEquals(b.weight, 4)
    assertEquals(b.owner, knight)


class MagicWeaponTest extends munit.FunSuite:
  var knight: Option[Character] = None
  var wand: Option[MagicWeapon] = None
  var staff: Option[MagicWeapon] = None

  override def beforeEach(context: BeforeEach): Unit = {
    wand = Some(new Wand("Elder Wand", 10, 1, None, 40))
    knight = Some(new Knight("Juan", 90, 10, 55, wand, List("potion")))
    staff = Some(new Staff("Oak Staff", 15, 5, knight, 25))
  }

  test("Wand fulfills the MagicWeapon contract"):
    val w = wand.get
    assertEquals(w.name, "Elder Wand")
    assertEquals(w.attack, 10)
    assertEquals(w.weight, 1)
    assertEquals(w.owner, None)
    assertEquals(w.magicAttack, 40)

  test("Staff fulfills the MagicWeapon contract"):
    val s = staff.get
    assertEquals(s.name, "Oak Staff")
    assertEquals(s.attack, 15)
    assertEquals(s.weight, 5)
    assertEquals(s.owner, knight)
    assertEquals(s.magicAttack, 25)