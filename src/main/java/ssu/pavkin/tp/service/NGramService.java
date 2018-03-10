package ssu.pavkin.tp.service;

import java.util.Map;
import java.util.Set;

public interface NGramService {

	Map<String, Set<String>> getNGramMap(String path, int scale);

	Set<String> getPossibleWords(String word, Map<String, Set<String>> map, int scale);
}
