package com.pronoun.pronouns

import org.scalatest.{Matchers, WordSpec}
import com.pronoun.pronouns.Utils._

class UtilsSpec extends WordSpec with Matchers {

  "findWithSubject should find the correct pronouns given only a subject pronoun" in {
    findWithSubject("she") should be(Some(Pronouns.she))
    findWithSubject("he") should be(Some(Pronouns.he))
    findWithSubject("they") should be(Some(Pronouns.they))
    findWithSubject("ze") should be(Some(Pronouns.ze))
    findWithSubject("blah") should be(None)
  }

  "findWithSubjectAndObject should find the correct pronouns given subject and object pronouns" in {
    findWithSubjectAndObject("she", "her") should be(Some(Pronouns.she))
    findWithSubjectAndObject("he", "him") should be(Some(Pronouns.he))
    findWithSubjectAndObject("they", "them") should be(Some(Pronouns.they))
    findWithSubjectAndObject("ze", "zir") should be(Some(Pronouns.ze))
    findWithSubjectAndObject("ze", "hir") should be(Some(Pronouns.ze2))
    findWithSubjectAndObject("she", "123") should be(None)
  }

  "parseSecondPronoun should parse a second pronoun added to a path" in {
    parseSecondPronoun("she") should be(Some(Pronouns.she))
    parseSecondPronoun("she/her") should be(Some(Pronouns.she))
    parseSecondPronoun("she/her/") should be(Some(Pronouns.she))
    parseSecondPronoun("she/123") should be(None)
    parseSecondPronoun("she/her/hers") should be(None)
  }

}
