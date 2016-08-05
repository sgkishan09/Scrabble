import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Util {
	public static HashMap<String, ArrayList<String>> clusters = new HashMap<String, ArrayList<String>>();

	public static int getScore(char x) {
		for (String score : ScrabbleConstants.charScore.keySet()) {
			if (score.contains(x + ""))
				return ScrabbleConstants.charScore.get(score);
		}
		return 0;
	}

	public ArrayList<String> readFile(String path) throws Exception {
		return (ArrayList<String>) Files.readAllLines(Paths.get(path));
	}

	public static String sort(String input) {
		char[] charArray = input.toCharArray();
		Arrays.sort(charArray);
		return new String(charArray);
	}
	public static void generateClusters(ArrayList<String> words) {
		for (String word : words) {
			if (clusters.containsKey(sort(word)))
				clusters.get(sort(word)).add(word);
			else {
				ArrayList<String> cluster = new ArrayList<String>();
				cluster.add(word);
				clusters.put(sort(word), cluster);
			}
		}
	}
}
