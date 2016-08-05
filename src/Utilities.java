
public class Utilities {
	public static int getScore(char x) {
		for (String score : ScrabbleConstants.charScore.keySet()) {
			if (score.contains(x + ""))
				return ScrabbleConstants.charScore.get(score);
		}
		return 0;
	}
}
