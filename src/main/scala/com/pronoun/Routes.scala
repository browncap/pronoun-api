package com.pronoun

import cats.effect.Sync
import org.http4s.HttpRoutes
import org.http4s.dsl.Http4sDsl
import com.pronoun.pronouns._
import com.pronoun.pronouns.Encoders._

object PronounRoutes {
  def routes[F[_]](implicit F: Sync[F]): HttpRoutes[F] = {
    val dsl = new Http4sDsl[F] {}
    import dsl._

    HttpRoutes.of[F] {
      case GET -> Root / subject / obj / possessive / possessiveDeterminer / reflexive =>
        Utils.findPronoun(subject, obj, possessive, possessiveDeterminer, reflexive).fold(NotFound())(succ => Ok(succ))

      case GET -> Root / "all" =>
        Ok(Utils.allPronouns)

      case GET -> Root / subject / obj :? SecondPronounParam(second) =>
        (for {
          firstPronoun <- Utils.findWithSubjectAndObject(subject, obj)
          secondPronoun <- Utils.parseSecondPronoun(second)
        } yield List(firstPronoun, secondPronoun)).fold(NotFound())(succ => Ok(succ))

      case GET -> Root / SubjectPronounVar(pronoun) :? SecondPronounParam(subject) =>
        val secondPronoun = Utils.parseSecondPronoun(subject)
        secondPronoun.fold(NotFound())(p => Ok(List(pronoun, p)))

      case GET -> Root / SubjectPronounVar(pronoun) =>
        Ok(pronoun)

      case GET -> Root / subject / obj =>
        Utils.findWithSubjectAndObject(subject, obj).fold(NotFound())(p => Ok(p))

    }
  }
}
