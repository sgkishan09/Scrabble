import java.util.ArrayList;
import java.util.List;

public class Rack {
	private List<Tile> tiles;

	public Rack(String str) {
		char[] letters = str.toCharArray();
		List<Tile> constituentTiles = new ArrayList<>();
		for (char letter : letters) {
			constituentTiles.add(new Tile(letter, Util.getScore(letter)));
		}
		this.tiles = constituentTiles;
	}

	public int getScore() {
		int score = 0;
		for (Tile letter : tiles) {
			score += letter.getScore();
		}
		return score;
	}

	public String toString() {
		String str = "";
		for (Tile letter : tiles) {
			str += letter.toString();
		}
		return str;
	}

	public String getMaxWord() {
		return Util.getMaxWord(this.toString(), "..................");
	}
}
