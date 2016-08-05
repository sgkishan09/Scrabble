import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Util {
	public static HashMap<Integer, ArrayList<String>> clusters = new HashMap<Integer, ArrayList<String>>();
	static {
		try {
			generateClusters(readFile("c:\\Projects\\sowpods.txt"));
		} catch (Exception e) {
		}
	}

	public static String getMaxWord(String input) {
		int maxScore = Integer.MIN_VALUE;
		String maxWord = "";
		if (Util.isExistWord(input))
			return input;

		// ABCD
		else {
			for (int i = 0; i < input.length(); i++) {
				StringBuilder sb = new StringBuilder(input);
				sb.deleteCharAt(i);
				String word = getMaxWord(sb.toString());
				int currentScore = Util.getScore(word);
				if (currentScore > maxScore) {
					maxWord = word;
					maxScore = currentScore;
				}
			}
		}
		return maxWord;
	}

	public static boolean isExistWord(String word) {
		// int noOfSpaces = word.length() - word.trim().length();
		int wordHash = Util.hash(word);
		for (Integer cluster : Util.clusters.keySet()) {
			if (cluster % wordHash == 0 && Util.clusters.get(cluster).get(0).length() == word.length()) {
				return true;
			}
		}
		return false;
	}

	public static int getScore(String word) {
		int sum = 0;
		char[] charArray = word.toCharArray();
		for (char x : charArray) {
			sum += getScore(x);
		}
		return sum;
	}

	public static int getScore(char x) {
		for (String score : ScrabbleConstants.charScore.keySet()) {
			if (score.contains(x + ""))
				return ScrabbleConstants.charScore.get(score);
		}
		return 0;
	}

	public static ArrayList<String> readFile(String path) throws Exception {
		return (ArrayList<String>) Files.readAllLines(Paths.get(path));
	}

	public static int hash(String input) {
		int[] primes26 = { 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97,
				101, 103 };
		char[] charArray = input.toCharArray();
		int result = 1;
		for (char element : charArray) {
			if (Character.isLetter(element)) {
				result = result * primes26[element - 'A'];
			} else {
				result = result * 1;
			}
		}
		return result;
	}

	public static void generateClusters(ArrayList<String> words) {
		for (String word : words) {
			if (clusters.containsKey(hash(word)))
				clusters.get(hash(word)).add(word);
			else {
				ArrayList<String> cluster = new ArrayList<String>();
				cluster.add(word);
				clusters.put(hash(word), cluster);
			}
		}
	}
}
