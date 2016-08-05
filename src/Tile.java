
public class Tile {
	char alphabet;
	int score;

	public Tile() {
		alphabet = ' ';
		score = 0;
	}

	public Tile(char alphabet, int score) {
		this.alphabet = alphabet;
		this.score = score;
	}

	public String toString() {
		return alphabet + "";
	}

	public int getScore() {
		return score;
	}

}
