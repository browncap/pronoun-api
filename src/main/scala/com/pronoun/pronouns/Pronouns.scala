package com.pronoun.pronouns

import cats.kernel.Eq

private[pronouns] object Pronouns {

  final case class Pronoun(
    subject: String,
    `object`: String,
    possessive: String,
    possessiveDeterminer: String,
    reflexive: String
  )
  object Pronoun {
    implicit val eqFoo: Eq[Pronoun] = Eq.fromUniversalEquals
  }

  val ae = Pronoun("ae", "aer", "aers", "aer", "aerself")
  val co = Pronoun("co", "co", "cos", "cos", "coself")
  val e = Pronoun("e", "em", "eirs", "eir", "emself")
  val en = Pronoun("en", "en", "ens", "ens", "enself")
  val ey = Pronoun("ey", "em", "eirs", "eir", "eirself")
  val fae = Pronoun("fae", "faer", "faers", "faer", "faerself")
  val fey = Pronoun("fey", "fem", "feirs", "feir", "feirself")
  val he = Pronoun("he", "him", "his", "his", "himself")
  val kit = Pronoun("kit", "kit", "kits", "kits", "kitself")
  val ne = Pronoun("ne", "nem", "nirs", "nir", "nemself")
  val peh = Pronoun("peh", "pehm", "peh's", "peh's", "pehself")
  val per = Pronoun("per", "per", "pers", "per", "perself")
  val se = Pronoun("se", "sim", "sers", "ser", "serself")
  val she = Pronoun("she", "her", "hers", "her", "herself")
  val si = Pronoun("si", "hyr", "hyrs", "hyr", "hyrself")
  val sie = Pronoun("sie", "hir", "hirs", "hir", "hirself")
  val tey = Pronoun("tey", "her", "ters", "tem", "terself")
  val they = Pronoun("they", "them", "theirs", "their", "themselves")
  val they2 = Pronoun("they", "them", "theirs", "their", "themself")
  val ve = Pronoun("ve", "ver", "vis", "vis", "verself")
  val ve2 = Pronoun("ve", "vem", "virs", "vir", "vemself")
  val vi = Pronoun("vi", "ver", "vers", "ver", "verself")
  val vi2 = Pronoun("vi", "vim", "virs", "vir", "vimself")
  val vi3 = Pronoun("vi", "vim", "vims", "vim", "vimself")
  val xe = Pronoun("xe", "xem", "xyrs", "xyr", "xemself")
  val xey = Pronoun("xey", "xem", "xyrs", "xyr", "xemself")
  val xey2 = Pronoun("xey", "xem", "xeirs", "xeir", "xemself")
  val xie = Pronoun("xie", "xer", "xers", "xer", "xerself")
  val yo = Pronoun("yo", "yo", "yos", "yos", "yosself")
  val ze = Pronoun("ze", "zir", "zirs", "zir", "zirself")
  val ze2 = Pronoun("ze", "hir", "hirs", "hir", "hirself")
  val ze3 = Pronoun("ze", "zem", "zes", "zes", "zirself")
  val ze4 = Pronoun("ze", "mer", "zers", "zer", "zemself")
  val zee = Pronoun("zee", "zed", "zetas", "zeta", "zedself")
  val zie = Pronoun("zie", "zir", "zirs", "zir", "zirself")
  val zie2 = Pronoun("zie", "hir", "hirs", "hir", "hirself")
  val zie3 = Pronoun("zie", "zem", "zes", "zes", "zirself")

  val pronouns = List(
    ae, e, en, ey,
    fae, fey, he, kit,
    ne, peh, per, sie,
    se, she, tey, they,
    they2, ve, ve2, vi,
    vi2, vi3, xie, xe,
    xey, xey2, yo, ze,
    ze2, ze3, ze4, zee,
    zie, zie2, zie3
  )

}
