package com.smartkyc.stemmers.azerbaijani;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AzerbaijaniStemmerTest
{
	final AzerbaijaniStemmer stemmer = new AzerbaijaniStemmer();

	@Test
	void testStemmer()
	{
		final String word7 = stemmer.stem("ailələrimizin");
		assertEquals("ailə", word7);

		final String word10 = stemmer.stem("çiynində");
		assertEquals("çiynində", word10);

		final String word = stemmer.stem("insanların");
		assertEquals("insan", word);

		final String word1 = stemmer.stem("qurbanlarının");
		assertEquals("qurban", word1);

		final String word3 = stemmer.stem("oğulsunuz");
		assertEquals("oğul", word3);

		final String word4 = stemmer.stem("sözümüzü");
		assertEquals("söz", word4);

		final String word5 = stemmer.stem("gedək");
		assertEquals("get", word5);

		final String word6 = stemmer.stem("sahəsindəki");
		assertEquals("sahə", word6);

		final String word8 = stemmer.stem("üstünlüyümüzü");
		assertEquals("üstünlük", word8);

		final String word9 = stemmer.stem("gətirlməsi");
		assertEquals("gətirl", word9);

		final String word11 = stemmer.stem("ənənəsini");
		assertEquals("ənənə", word11);

		final String word12 = stemmer.stem("münasibətlərdən");
		assertEquals("münasibət", word12);

		final String word13 = stemmer.stem("kommersiya");
		assertEquals("kommersiya", word13);

		final String word14 = stemmer.stem("nəzarətimizdən");
		assertEquals("nəzarət", word14);

		final String word15 = stemmer.stem("müharibəyə");
		assertEquals("müharibə", word15);

		final String word16 = stemmer.stem("torpaqlarımızı");
		assertEquals("torpaq", word16);

		final String word17 = stemmer.stem("millətçilərinin");
		assertEquals("millətçi", word17);

		final String word18 = stemmer.stem("istiqamətlərdən");
		assertEquals("istiqamət", word18);

		final String word19 = stemmer.stem("çatdırılmasının");
		assertEquals("çatdırılma", word19);

		final String word20 = stemmer.stem("pandemiyasına");
		assertEquals("pandemiya", word20);

		final String word21 = stemmer.stem("faiz");
		assertEquals("faiz", word21);

		final String word22 = stemmer.stem("artıb");
		assertEquals("art", word22);
	}

	@Test
	void stem_UpperCaseWord_ShouldPreserveCapitalization()
	{
		assertEquals("Pandemiya", stemmer.stem("Pandemiyasına"));

		assertEquals("Istiqamət", stemmer.stem("Istiqamətlərdən"));

		assertEquals("TorPaq", stemmer.stem("TorPaqlarımızı"));

		assertEquals("Ailə", stemmer.stem("Ailələrimizin"));

		assertEquals("ÇIYNINDƏ", stemmer.stem("ÇIYNINDƏ"));

		assertEquals("OğUl", stemmer.stem("OğUlSuNuZ"));

		assertEquals("SöZ", stemmer.stem("SöZüMüZü"));

		assertEquals("GeT", stemmer.stem("GeDəK"));

		assertEquals("SaHə", stemmer.stem("SaHəSiNdƏkİ"));

		assertEquals("ÜsTüNlÜk", stemmer.stem("ÜsTüNlÜyÜmÜzÜ"));

		assertEquals("GəTiRl", stemmer.stem("GəTiRlMəSi"));

		assertEquals("ƏnƏnƏ", stemmer.stem("ƏnƏnƏsİnİ"));

		assertEquals("MüNaSiBəT", stemmer.stem("MüNaSiBəTlƏrDəN"));
	}
}
