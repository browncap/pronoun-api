package com.pronoun.config


final case class PronounConfig(
  http: HttpConfig
)

final case class HttpConfig(
  port: Int,
  host: String
)
