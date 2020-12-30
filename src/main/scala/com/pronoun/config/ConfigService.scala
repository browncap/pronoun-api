package com.pronoun.config

import cats.effect._
import cats.implicits._
import pureconfig._
import pureconfig.generic.auto._
import com.typesafe.config.ConfigFactory

object ConfigService {
  def getConfig[F[_]](implicit F: ConcurrentEffect[F]): F[PronounConfig] = SetupConfig.loadConfig[F]
}

private[config] object SetupConfig {

  def loadConfig[F[_]](implicit F: Sync[F]): F[PronounConfig] = for {
    classLoader <- Sync[F].delay(ConfigFactory.load(getClass().getClassLoader()))
    out <- Sync[F].delay(loadConfigOrThrow[PronounConfig](classLoader, "com.pronoun"))
  } yield out

}