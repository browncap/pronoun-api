package com.pronoun.pronouns

import cats.effect.IO
import cats.implicits._
import cats.syntax.eq._
import com.pronoun.pronouns.Pronouns.Pronoun
import com.pronoun.pronouns.Pronouns._
import io.circe.generic.semiauto.deriveDecoder
import io.circe.{Decoder, Json}
import io.circe.syntax._
import com.pronoun.PronounRoutes
import org.http4s.circe.CirceEntityDecoder._
import org.http4s.implicits._
import org.http4s.{Method, Request, Uri}
import org.scalacheck.{Arbitrary, Gen}
import org.scalatest.{Matchers, WordSpec}
import org.scalatestplus.scalacheck.ScalaCheckPropertyChecks

class RoutesSpec extends WordSpec with Matchers with ScalaCheckPropertyChecks {

  implicit val pronounArb: Arbitrary[Pronoun] = Arbitrary {
    val randNum = Gen.choose(0, pronouns.length - 1)
    randNum.map(num => pronouns.apply(num))
  }

  implicit val pronounDecoder: Decoder[Pronoun] = deriveDecoder[Pronoun]

  def toPronounJson(pronoun: Pronoun): Json =
    Json.obj(fields =
      "subject" := pronoun.subject.show,
      "object" := pronoun.`object`.show,
      "possessive" := pronoun.possessive.show,
      "possessiveDeterminer" := pronoun.possessiveDeterminer.show,
      "reflexive" := pronoun.reflexive.show)

  "return expected json response for /subject/object/possessive/possessiveDeterminer/reflexive endpoint" in {
    forAll { p: Pronoun =>

      val routes = PronounRoutes.routes[IO].orNotFound
      val req = Request[IO](method = Method.GET, uri = Uri.unsafeFromString(s"/${p.subject}/${p.`object`}/${p.possessive}/${p.possessiveDeterminer}/${p.reflexive}"))

      val routeResult = routes.run(req).unsafeRunSync
      val routePronoun = routeResult.as[Pronoun].unsafeRunSync

      routeResult.status.isSuccess should be(true)
      routePronoun should be(p)
    }
  }

  "return expected json response for /all endpoint" in {
    val routes = PronounRoutes.routes[IO].orNotFound
    val req = Request[IO](method = Method.GET, uri = Uri.unsafeFromString(s"/all"))

    val result = Json.fromValues(pronouns.map(toPronounJson(_)))
    val routeResult = routes.run(req).unsafeRunSync

    routeResult.status.isSuccess should be(true)
    routeResult.as[Json].unsafeRunSync should be(result)
  }

  "return expected json response for /pronoun endpoint" in {
    forAll { p: Pronoun =>

      val routes = PronounRoutes.routes[IO].orNotFound
      val req = Request[IO](method = Method.GET, uri = Uri.unsafeFromString(s"/${p.subject}"))

      val routeResult = routes.run(req).unsafeRunSync
      val routePronoun = routeResult.as[Pronoun].unsafeRunSync

      routeResult.status.isSuccess should be(true)
      routePronoun.subject should be(p.subject)
    }
  }

  "return expected json response for /subject/object endpoint" in {
    forAll { p: Pronoun =>

      val routes = PronounRoutes.routes[IO].orNotFound
      val req = Request[IO](method = Method.GET, uri = Uri.unsafeFromString(s"/${p.subject}/${p.`object`}"))

      val routeResult = routes.run(req).unsafeRunSync
      val routePronoun = routeResult.as[Pronoun].unsafeRunSync

      routeResult.status.isSuccess should be(true)
      routePronoun.subject should be(p.subject)
      routePronoun.`object` should be(p.`object`)
    }
  }

  "return expected json response for /subject?or=subject endpoint" in {
    forAll { (p1: Pronoun, p2: Pronoun) =>

      val routes = PronounRoutes.routes[IO].orNotFound
      val req = Request[IO](method = Method.GET, uri = Uri.unsafeFromString(s"/${p1.subject}?or=${p2.subject}"))

      val routeResult = routes.run(req).unsafeRunSync
      val routePronouns = routeResult.as[List[Pronoun]].unsafeRunSync.map(_.subject)

      routeResult.status.isSuccess should be(true)
      routePronouns should be(List(p1.subject, p2.subject))
    }
  }

  "return expected json response for /subject/object?or=subject endpoint" in {
    forAll { (p1: Pronoun, p2: Pronoun) =>

      val routes = PronounRoutes.routes[IO].orNotFound
      val req = Request[IO](method = Method.GET, uri = Uri.unsafeFromString(s"/${p1.subject}/${p1.`object`}?or=${p2.subject}"))

      val routeResult = routes.run(req).unsafeRunSync
      val routePronouns = routeResult.as[List[Pronoun]].unsafeRunSync

      routeResult.status.isSuccess should be(true)
      routePronouns.filter(p => p.`object` === p1.`object` || p.subject === p2.subject) should have length (2)
    }
  }

  "return expected json response for /subject/object?or=subject/object endpoint" in {
    forAll { (p1: Pronoun, p2: Pronoun) =>

      val routes = PronounRoutes.routes[IO].orNotFound
      val req = Request[IO](method = Method.GET, uri = Uri.unsafeFromString(s"/${p1.subject}/${p1.`object`}?or=${p2.subject}/${p2.`object`}"))

      val routeResult = routes.run(req).unsafeRunSync
      val routePronouns = routeResult.as[List[Pronoun]].unsafeRunSync.map(_.`object`)

      routeResult.status.isSuccess should be(true)
      routePronouns should be(List(p1.`object`, p2.`object`))
    }
  }
}