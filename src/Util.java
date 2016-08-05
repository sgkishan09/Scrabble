import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Util {
	public static HashMap<Integer, ArrayList<String>> clusters = new HashMap<Integer, ArrayList<String>>();

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

	public static int hash(String input) {
		int[] primes26 = {3,5,7,11,13,17,19,23,29,31,37,41,43,47,53,59,61,67,71,73,79,83,89,97,101,103};
		char[] charArray = input.toCharArray();
		int result = 1;
		for(char element : charArray)
		{
			if(Character.isLetter(element))
			{
				result=result*primes26[element-'A'];
			}
			else
			{
				result = result*2;
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
