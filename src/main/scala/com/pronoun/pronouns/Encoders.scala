package com.pronoun.pronouns

import Pronouns._
import cats.Applicative
import io.circe.Encoder
import io.circe.generic.semiauto.deriveEncoder
import org.http4s.EntityEncoder
import org.http4s.circe.jsonEncoderOf

object Encoders {

  implicit val encoder: Encoder[Pronoun] = deriveEncoder[Pronoun]
  implicit def entityPronounEncoder[F[_]: Applicative]: EntityEncoder[F, Pronoun] =
    jsonEncoderOf[F, Pronoun]
  implicit def entityListPronounEncoder[F[_]: Applicative]: EntityEncoder[F, List[Pronoun]] =
    jsonEncoderOf[F, List[Pronoun]]

}