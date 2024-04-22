package com.smartkyc.stemmers.azerbaijani;

import lombok.extern.slf4j.Slf4j;

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

@Slf4j
public class AzerbaijaniStemmer
{
	private final Set<String> words = new HashSet<>();

	private final List<String> suffixes = new ArrayList<>();

	public AzerbaijaniStemmer() {
		loadSuffixes();
		loadWords();
	}

	public String stem(final String word)
	{
		final String processedWord = processWord(word);

		if (processedWord.length() < 2) {
			return word;
		}

		return processedWord;
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

	private void loadSuffixes()
	{
		try (InputStream inputStream = Objects.requireNonNull(getClass().getResourceAsStream("/AzerbaijaniStemmer/suffix.txt"));
				BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
			String line;
			while ((line = reader.readLine()) != null) {
				suffixes.add(line);
			}
		} catch (final IOException e) {
			log.error("Error loading suffixes file", e);
		}
	}

	private void loadWords()
	{
		try (InputStream inputStream = Objects.requireNonNull(getClass().getResourceAsStream("/AzerbaijaniStemmer/words.txt"));
				BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
			String line;
			while ((line = reader.readLine()) != null) {
				words.add(line);
			}
		} catch (final IOException e) {
			log.error("Error loading words file", e);
		}
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