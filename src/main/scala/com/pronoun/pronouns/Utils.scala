package com.pronoun.pronouns

import cats.implicits._
import Pronouns._

object Utils {

  lazy val allPronouns = pronouns

  def findPronoun(subject: String, `object`: String, possessive: String, possessiveDeterminer: String, reflexive: String): Option[Pronoun] = {
    val pronoun = Pronoun(subject, `object`, possessive, possessiveDeterminer, reflexive)
    pronouns.find(_ === pronoun)
  }

  def findWithSubject(subject: String): Option[Pronoun] =
    pronouns.find(_.subject === subject)

  def findWithSubjectAndObject(subject: String, `object`: String): Option[Pronoun] =
    pronouns.find(p => p.subject === subject && p.`object` === `object`)

  def parseSecondPronoun(path: String): Option[Pronoun] = {
    path.split('/').toList match {
      case List(s, o) => findWithSubjectAndObject(s, o)
      case List(s) => findWithSubject(s)
      case _ => None
    }
  }
}
