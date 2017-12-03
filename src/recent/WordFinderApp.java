package recent;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class WordFinderApp
{
	public static void main(String[] args) throws FileNotFoundException
	{
		WordFinder test = new WordFinder();
		Scanner scan = new Scanner(System.in);
		System.out.print("Enter letters to query (space or no space separated, alphabetic characters only): ");
		String s = scan.nextLine();
		s = s.toLowerCase();
		s = s.replaceAll("\\s", "");
		int i = s.length();
		scan.close();
		test.query(s);
		test.sortedOutput(i);
	}
}
