package com.pronoun.pronouns

import org.http4s.dsl.impl.QueryParamDecoderMatcher
import Pronouns._
import scala.util.control.NoStackTrace

object SecondPronounParam extends QueryParamDecoderMatcher[String]("or")

object SubjectPronounVar {
  def unapply(str: String): Option[Pronoun] = {
    if (!str.trim.isEmpty)
      Utils.findWithSubject(str.toLowerCase)
    else
      None
  }
}
