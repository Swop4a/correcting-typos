package ssu.pavkin.tp.utils;

import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class Utils {

	public static Set<String> getWordsBySignature(String signature, int n, Map<String, Set<String>> nMap) {
		Set<String> words = new HashSet<>();
		for (int i = 0; i < signature.length() - n  + 1; i++) {
			Set<String> obj = nMap.get(signature.substring(i, i + n));
			if (Objects.nonNull(obj)) {
				words.addAll(obj);
			}
		}
		return words;
	}
}
