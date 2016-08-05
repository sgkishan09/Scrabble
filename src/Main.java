import java.util.regex.*;

public class Main {
	public static void main(String[] args) {
		String word = Util.getMaxWordwithPattern("WAORCLD", "W...D");
		System.out.println(word + "\t" + Util.getScore(word));

	}
}
