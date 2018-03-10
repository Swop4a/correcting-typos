package ssu.pavkin.tp.service.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ssu.pavkin.tp.service.NGramService;

public class NGramServiceImpl implements NGramService {

	private static final Logger LOG = LoggerFactory.getLogger(NGramServiceImpl.class);

	@Override
	public Map<String, Set<String>> getNGramMap(String path, int n) {
		try (Scanner scanner = new Scanner(new File(path))) {
			Map<String, Set<String>> threeGramMap = new HashMap<>();
			while (scanner.hasNextLine()) {
				String word = scanner.nextLine().trim();
				for (int i = 0; i < word.length() - n - 1; i++) { // wlen - n?
					String threeGram = word.substring(i, i + n);
					if (threeGramMap.containsKey(threeGram)) {
						Set<String> words = threeGramMap.get(threeGram);
						words.add(word);
						threeGramMap.put(threeGram, words);
					} else {
						threeGramMap.put(threeGram, new HashSet<String>() {{
							add(word);
						}});
					}
				}
			}
			return threeGramMap;
		} catch (FileNotFoundException e) {
			System.err.println(e.getCause().getMessage());
			return Collections.emptyMap();
		}
	}

	@Override
	public Set<String> getPossibleWords(String word, Map<String, Set<String>> map, int n) {
		return map.entrySet().stream()
			.map(Entry::getValue)
			.reduce((acc, v) -> {
				acc.addAll(v);
				return acc;
			})
			.orElse(Collections.emptySet());
	}
}
