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

	public String processWord(String originalWord)
	{
		String processedWord = originalWord.toLowerCase(Locale.forLanguageTag("AZ"));
		processedWord = removeSuffixes(processedWord);
		processedWord = convertLastLetters(processedWord);

		for (final String suffix : suffixes) {
			// If word ends with current suffix, remove the suffix and stem again
			if (processedWord.endsWith(suffix)) {
				if (words.contains(processedWord)) {
					processedWord = restoreCapitalization(originalWord, processedWord);
					return processedWord;
				}
				processedWord = processWord(processedWord.substring(0, processedWord.lastIndexOf(suffix)));
			}
		}

		processedWord = restoreCapitalization(originalWord, processedWord);

		return processedWord;
	}

	private String restoreCapitalization(String originalWord, String convertedWord)
	{
		if (!hasUppercase(originalWord)) {
			return convertedWord;
		}

		StringBuilder restored = new StringBuilder(convertedWord.length());

		for (int i = 0; i < convertedWord.length(); i++) {
			char charAtIndex = convertedWord.charAt(i);
			if (Character.isUpperCase(originalWord.charAt(i))) {
				restored.append(Character.toUpperCase(charAtIndex));
			} else {
				restored.append(charAtIndex);
			}
		}
		return restored.toString();
	}

	private boolean hasUppercase(String word) {
		for (int i = 0; i < word.length(); i++) {
			if (Character.isUpperCase(word.charAt(i))) {
				return true;
			}
		}
		return false;
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
	private String removeSuffixes(String word)
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

	private String convertLastLetters(final String word)
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