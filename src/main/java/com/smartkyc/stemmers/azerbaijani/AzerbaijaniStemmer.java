package com.smartkyc.stemmers.azerbaijani;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.Set;

public class AzerbaijaniStemmer
{
	private static final Logger log = LoggerFactory.getLogger(AzerbaijaniStemmer.class);

	private final Set<String> words = loadWords();

	private final List<String> suffixes = loadSuffixes();

	public String stem(final String word)
	{
		try {
			final String processedWord = processWord(word);

			if (processedWord.length() < 2) {
				return word;
			}

			return processedWord;
		} catch (final Exception e) {
			log.debug("Failed to stem word: {}", word, e);
			return word;
		}
	}

	public String processWord(String word)
	{
		word = word.toLowerCase(Locale.forLanguageTag("AZ"));
		word = suffix(word);
		word = converter(word);

		for (final String suffix : suffixes) {
			// If word ends with current suffix, remove the suffix and stem again
			if (word.endsWith(suffix)) {
				if (words.contains(word)) {
					return word;
				}
				word = processWord(word.substring(0, word.lastIndexOf(suffix)));
			}
		}

		return word;
	}

	private List<String> loadSuffixes()
	{
		final List<String> loadedSuffixes = new ArrayList<>();
		try (InputStream inputStream = Objects.requireNonNull(getClass().getResourceAsStream("/AzerbaijaniStemmer/suffix.txt"));
				BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
			String line;
			while ((line = reader.readLine()) != null) {
				loadedSuffixes.add(line);
			}
		} catch (final IOException e) {
			log.error("Error loading suffixes file", e);
		}
		return loadedSuffixes;
	}

	private Set<String> loadWords()
	{
		final Set<String> loadedWords = new HashSet<>();
		try (InputStream inputStream = Objects.requireNonNull(getClass().getResourceAsStream("/AzerbaijaniStemmer/words.txt"));
				BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
			String line;
			while ((line = reader.readLine()) != null) {
				loadedWords.add(line);
			}
		} catch (final IOException e) {
			log.error("Error loading words file", e);
		}
		return loadedWords;
	}

	// Removes one suffix at a time
	private String suffix(String word)
	{
		for (final String suffix : suffixes) {
			// If the word ends with the particular suffix, create a new word by removing that suffix
			if (word.endsWith(suffix)) {
				final String substring = word.substring(0, word.lastIndexOf(suffix));
				if (word.endsWith(suffix) && words.contains(substring)) {
					word = substring;
					return word;
				}
			}
		}
		return word;
	}

	private String converter(final String word)
	{
		if (word.endsWith("lığ") || word.endsWith("luğ") || word.endsWith("lağ") || word.endsWith("cığ")) {
			final char[] l = word.toCharArray();
			l[word.length() - 1] = 'q';
			return new String(l);
		}
		if (word.endsWith("liy") || word.endsWith("lüy")) {
			final char[] l = word.toCharArray();
			l[word.length() - 1] = 'k';
			return new String(l);
		}
		if (word.endsWith("cağ")) {
			final char[] l = word.toCharArray();
			l[word.length() - 1] = 'q';
			return new String(l);
		}
		if (word.endsWith("cəy")) {
			final char[] l = word.toCharArray();
			l[word.length() - 1] = 'k';
			return new String(l);
		}
		if (word.endsWith("ığ") || word.endsWith("uğ") || word.endsWith("ağ")) {
			final char[] l = word.toCharArray();
			l[word.length() - 1] = 'q';
			return new String(l);
		}
		if (word.endsWith("iy") || word.endsWith("üy") || word.endsWith("əy")) {
			final char[] l = word.toCharArray();
			l[word.length() - 1] = 'k';
			return new String(l);
		}
		if (word.equals("ed")) {
			final char[] l = word.toCharArray();
			l[1] = 't';
			return new String(l);
		}
		if (word.equals("ged")) {
			final char[] l = word.toCharArray();
			l[2] = 't';
			return new String(l);
		}
		return word;
	}

}