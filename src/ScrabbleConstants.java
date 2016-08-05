import java.util.*;

public class ScrabbleConstants {
	static HashMap<String, Integer> charScore;
	static {
		charScore = new HashMap<>();
		charScore.put(" ", 0);
		charScore.put("EAIONRTLSU", 1);
		charScore.put("DG", 2);
		charScore.put("BCMP", 3);
		charScore.put("FHWVY", 4);
		charScore.put("K", 5);
		charScore.put("JX", 8);
		charScore.put("QZ", 10);
	}
}
