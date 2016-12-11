package org.thinpole;

import java.util.ArrayList;
import java.util.List;

public class SearchWords {
	public static List<String> search(String toFind, String text) {
		List<String> stringList = new ArrayList<>();

		// first separate into sentences
		String[] stringArray = splitText(text);

		// then check every sentence & add those that follow the rule
		for (int i=0; i<stringArray.length; i++) {
			if (checkSentence(toFind, stringArray[i])) {
				stringList.add(stringArray[i]);
			}
		}
		
		// add all of them just to check
		/*for (int i = 0; i < stringArray.length; i++) {
			stringList.add(stringArray[i]);
		}*/

		return stringList;
	}

	private static String[] splitText(String text) {
		// first check delimiters
		List<Integer> indeces = new ArrayList<>();
		for (int i = 0; i < text.length(); i++) {
			if (text.charAt(i) == '.') {
				indeces.add(i + 1);
			}
		}
		// create Array
		String[] sentences = new String[indeces.size()];

		// split text into sentences
		int beginning = 0;
		for (int i = 0; i < indeces.size(); i++) {
			int end = 0;
			// check whether not the last one
			// and set the end of the current sentence
			if (i < indeces.size()) {
				end = indeces.get(i);
			} else {
				end = text.length();
			}
			sentences[i] = text.substring(beginning, end);
			// check whether not the last one
			// and set the beginning of the next sentence
			if (i + 1 < indeces.size()) {
				beginning = indeces.get(i) + 1;
			}
		}

		return sentences;
	}

	private static boolean checkSentence(String toFind, String sentence) {
		return sentence.toLowerCase().contains(toFind);
	}
}
