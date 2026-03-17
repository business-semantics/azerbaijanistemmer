package com.smartkyc.stemmers.azerbaijani;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AzerbaijaniStemmerTest
{
	private static final AzerbaijaniStemmer AZERBAIJANI_STEMMER = new AzerbaijaniStemmer();

	@Test
	void testStemmer()
	{
		final String word7 = AZERBAIJANI_STEMMER.stem("ailələrimizin");
		assertEquals("ailə", word7);

		final String word10 = AZERBAIJANI_STEMMER.stem("çiynində");
		assertEquals("çiynində", word10);

		final String word = AZERBAIJANI_STEMMER.stem("insanların");
		assertEquals("insan", word);

		final String word1 = AZERBAIJANI_STEMMER.stem("qurbanlarının");
		assertEquals("qurban", word1);

		final String word3 = AZERBAIJANI_STEMMER.stem("oğulsunuz");
		assertEquals("oğul", word3);

		final String word4 = AZERBAIJANI_STEMMER.stem("sözümüzü");
		assertEquals("söz", word4);

		final String word5 = AZERBAIJANI_STEMMER.stem("gedək");
		assertEquals("get", word5);

		final String word6 = AZERBAIJANI_STEMMER.stem("sahəsindəki");
		assertEquals("sahə", word6);

		final String word8 = AZERBAIJANI_STEMMER.stem("üstünlüyümüzü");
		assertEquals("üstünlük", word8);

		final String word9 = AZERBAIJANI_STEMMER.stem("gətirlməsi");
		assertEquals("gətirl", word9);

		final String word11 = AZERBAIJANI_STEMMER.stem("ənənəsini");
		assertEquals("ənənə", word11);

		final String word12 = AZERBAIJANI_STEMMER.stem("münasibətlərdən");
		assertEquals("münasibət", word12);

		final String word13 = AZERBAIJANI_STEMMER.stem("kommersiya");
		assertEquals("kommersiya", word13);

		final String word14 = AZERBAIJANI_STEMMER.stem("nəzarətimizdən");
		assertEquals("nəzarət", word14);

		final String word15 = AZERBAIJANI_STEMMER.stem("müharibəyə");
		assertEquals("müharibə", word15);

		final String word16 = AZERBAIJANI_STEMMER.stem("torpaqlarımızı");
		assertEquals("torpaq", word16);

		final String word17 = AZERBAIJANI_STEMMER.stem("millətçilərinin");
		assertEquals("millətçi", word17);

		final String word18 = AZERBAIJANI_STEMMER.stem("istiqamətlərdən");
		assertEquals("istiqamət", word18);

		final String word19 = AZERBAIJANI_STEMMER.stem("çatdırılmasının");
		assertEquals("çatdırılma", word19);

		final String word20 = AZERBAIJANI_STEMMER.stem("pandemiyasına");
		assertEquals("pandemiya", word20);

		final String word21 = AZERBAIJANI_STEMMER.stem("faiz");
		assertEquals("faiz", word21);

		final String word22 = AZERBAIJANI_STEMMER.stem("artıb");
		assertEquals("art", word22);
	}

	@Test
	void stem_UpperCaseWord_ShouldPreserveCapitalization()
	{
		assertEquals("Pandemiya", AZERBAIJANI_STEMMER.stem("Pandemiyasına"));

		assertEquals("Istiqamət", AZERBAIJANI_STEMMER.stem("Istiqamətlərdən"));

		assertEquals("TorPaq", AZERBAIJANI_STEMMER.stem("TorPaqlarımızı"));

		assertEquals("Ailə", AZERBAIJANI_STEMMER.stem("Ailələrimizin"));

		assertEquals("ÇIYNINDƏ", AZERBAIJANI_STEMMER.stem("ÇIYNINDƏ"));

		assertEquals("OğUl", AZERBAIJANI_STEMMER.stem("OğUlSuNuZ"));

		assertEquals("SöZ", AZERBAIJANI_STEMMER.stem("SöZüMüZü"));

		assertEquals("GeT", AZERBAIJANI_STEMMER.stem("GeDəK"));

		assertEquals("SaHə", AZERBAIJANI_STEMMER.stem("SaHəSiNdƏkİ"));

		assertEquals("ÜsTüNlÜk", AZERBAIJANI_STEMMER.stem("ÜsTüNlÜyÜmÜzÜ"));

		assertEquals("GəTiRl", AZERBAIJANI_STEMMER.stem("GəTiRlMəSi"));

		assertEquals("ƏnƏnƏ", AZERBAIJANI_STEMMER.stem("ƏnƏnƏsİnİ"));

		assertEquals("MüNaSiBəT", AZERBAIJANI_STEMMER.stem("MüNaSiBəTlƏrDəN"));
	}

	@Test
	void testCasingWithI() {
		// In Azerbaijani, 'i'.toUpperCase() should be 'İ' (dotted I)
		// 'I'.toLowerCase() should be 'ı' (dotless i)
		assertEquals("İNSAN", AZERBAIJANI_STEMMER.stem("İNSANLARIN"));
	}
}
