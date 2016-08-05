import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Util {
	public static HashMap<Integer, ArrayList<String>> clusters = new HashMap<Integer, ArrayList<String>>();
	static {
		try {
			generateClusters(readFile("c:\\Projects\\sowpods.txt"));
		} catch (Exception e) {
		}
	}

	public static String getMaxWord(String input, String pattern) {
		int maxScore = Integer.MIN_VALUE;
		String maxWord = "";
		if (Util.isExistWord(input))
			return input;
		else {
			String regex = "^" + generateRegex(pattern) + "$";
			Pattern regexpattern = Pattern.compile(regex);
			System.out.println(regex);
			for (int i = 0; i < input.length(); i++) {
				StringBuilder sb = new StringBuilder(input);
				sb.deleteCharAt(i);
				String word = getMaxWord(sb.toString(), pattern);
				int currentScore = Util.getScore(word);
				System.out.println(word + "\t Without Match");
				if (currentScore > maxScore) {
					Matcher m = regexpattern.matcher(word);
					if (m.find()) {
						maxWord = word;
						maxScore = currentScore;
						System.out.println(word + "\t After Match " + maxScore);
					}
				}
			}
		}
		return maxWord;
	}

	public static String getMaxWordwithPattern(String input, String pattern) {
		int hashWord = Util.hash(input);
		int maxScore = Integer.MIN_VALUE;
		String maxWord = "";
		Pattern regex = Pattern.compile(generateRegex(pattern));
		for (Integer cluster : clusters.keySet()) {
			if (hashWord % cluster == 0) {
				for (String word : clusters.get(cluster)) {
					Matcher m = regex.matcher(word);
					if (m.find()) {
						int currentScore = getScore(word);
						if (currentScore > maxScore) {
							maxScore = currentScore;
							maxWord = word;
						}
					}
				}
			}

		}
		return maxWord;
	}

	public static boolean isExistWord(String word) {
		// int noOfSpaces = word.length() - word.trim().length();
		int wordHash = Util.hash(word);
		for (int cluster : Util.clusters.keySet()) {
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
		int[] primes26 = { 2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89,
				97, 101, 103 };
		char[] charArray = input.toCharArray();
		int result = 1;
		for (char element : charArray) {
			if (Character.isLetter(element)) {
				result = result * primes26[element - 'A'];
			}
		}
		return result;
	}

	public static String generateRegex(String pattern) {
		String patternRegex = "";
		int count = 0;
		boolean flag = false;
		for (int i = 0; i < pattern.length(); i++) {
			if (Character.isAlphabetic(pattern.charAt(i))) {
				if (!flag) {
					patternRegex += "^.{0," + count + "}" + pattern.charAt(i);
					flag = true;
				} else {
					patternRegex += ".{" + count + "}" + pattern.charAt(i);
				}
				count = 0;
			} else
				count++;
		}
		patternRegex += ".{" + count + "}$";
		return patternRegex;
	}

	public static void generateClusters(ArrayList<String> words) {
		for (String word : words) {
			word = word.trim();
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
