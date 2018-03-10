package ssu.pavkin.tp;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import ssu.pavkin.tp.service.LevensteinService;
import ssu.pavkin.tp.service.NGramService;
import ssu.pavkin.tp.service.impl.LevensteinServiceImpl;
import ssu.pavkin.tp.service.impl.NGramServiceImpl;
import ssu.pavkin.tp.utils.Utils;

public class Application {

	private static final String PATH = "src\\main\\resources\\dictionary.txt";
	private static final int N_GRAM = 3;

	private Scanner scanner = new Scanner(System.in);

	private LevensteinService levensteinService = new LevensteinServiceImpl();
	private NGramService nGramService = new NGramServiceImpl();


	public static void main(String[] args) {
		new Application().run();
	}

	private void run() {
		Map<String, Set<String>> nGramMap = nGramService.getNGramMap(PATH, N_GRAM);
		while (true) {
			System.out.println("Enter word:");
			String word = scanner.next();
			Set<String> possibleWords = Utils.getWordsBySignature(word, N_GRAM, nGramMap);

			Map<String, Integer> wordToDistanceMap = new HashMap<>();
			possibleWords.forEach(w -> {
				int distance = levensteinService.getDistance(w, word);
				wordToDistanceMap.put(w, distance);
			});

			getSortedMapByValueForStringToIntMap(wordToDistanceMap)
				.forEach((k, v) -> System.out.println(String.format("Distance: %d, Word: %s", v, k)));
		}
	}

	private SortedMap<String, Integer> getSortedMapByValueForStringToIntMap(final Map<String, Integer> map) {
		Comparator<String> comparator = (o1, o2) -> {
			int i = map.get(o1).compareTo(map.get(o2));
			if (i == 0) {
				return 1;
			} else {
				return i;
			}
		};
		SortedMap<String, Integer> sortedMap = new TreeMap<>(comparator);
		sortedMap.putAll(map);
		return sortedMap;
	}
}
