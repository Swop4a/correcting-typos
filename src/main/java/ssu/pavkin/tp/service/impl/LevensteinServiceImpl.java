package ssu.pavkin.tp.service.impl;

import static java.lang.Math.min;

import java.util.ArrayList;
import java.util.List;
import ssu.pavkin.tp.service.LevensteinService;

public class LevensteinServiceImpl implements LevensteinService {

	public int getDistance(String s1, String s2) {
		List<List<Integer>> matrix = new ArrayList<>();
		for (int i = 0; i < s1.length() + 1; i++) {
			matrix.add(new ArrayList<>());
			for (int j = 0; j < s2.length() + 1; j++) {
				if (i == 0 && j == 0) {
					matrix.get(i).add(0);
				} else if (i == 0) {
					matrix.get(i).add(j);
				} else if (j == 0) {
					matrix.get(i).add(i);
				} else {
					matrix.get(i).add(min(
						matrix.get(i).get(j - 1) + 1, min(
							matrix.get(i - 1).get(j) + 1,
							matrix.get(i - 1).get(j - 1) + (s1.charAt(i - 1) == s2.charAt(j - 1) ? 0 : 1))));
				}
			}
		}
		return matrix.get(s1.length()).get(s2.length());
	}
}
