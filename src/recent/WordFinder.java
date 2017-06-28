package recent;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class WordFinder
{
	class wordNode // Simple node to store words in an adjacency linked list
	{
		private String word;
		private wordNode next;
		
		private wordNode(String word)
		{
			this.word = word;
			this.next = null;
		}
	}
	
	private wordNode[] masterList; // Index of words based on value
	public ArrayList<String> result; // Output list for words composed of input letters
	
	public WordFinder() throws FileNotFoundException
	{
		masterList = new wordNode[500];
		result = new ArrayList<String>();
		File words = new File("words.txt");
		populate(words);
	}
	
	public int stringValue(String s) // Defines string values based on cumulative character values
	{
		int value = 0;
		for (int i = 0; i < s.length(); i++)
		{
			if (!Character.isLetter(s.charAt(i))) continue;
			value += (int)(s.charAt(i)) - 97;
		}
		return value;
	}
	
	private void populate(File words) throws FileNotFoundException // Indexes master list with English words
	{
		Scanner scan = new Scanner(words);
		String s;
		while (scan.hasNext())
		{
			s = scan.next();
			int index = stringValue(s);
			wordNode n = new wordNode(s);
			if (masterList[index] == null) masterList[index] = n;
			else 
			{
				n.next = masterList[index];
				masterList[index] = n;
			}
		}
		scan.close();
	}
	
	private boolean validLetters(String s1, String s2) // Determines if a word can be composed of a combination of input characters
	{
		int[] alphabet = new int[26];
		int index;
		for (int i = 0; i < s1.length(); i++)
		{
			index = (int)(s1.charAt(i)) - 97;
			++alphabet[index]; 
		}
		for (int i = 0; i < s2.length(); i++)
		{
			index = (int)(s2.charAt(i)) - 97;
			--alphabet[index]; 
			if (alphabet[index] < 0) return false;
		}
		return true;
	}
	
	public void query(String s) // Main query algorithm
	{
		if (s.length() < 1) return;
		wordNode ptr;
		for (int i = 0; i < stringValue(s) + 1; i++)
		{
			ptr = masterList[i];
			while (ptr != null)
			{
				if (validLetters(s, ptr.word)) result.add(ptr.word);
				ptr = ptr.next;
			}
		}
		Collections.sort(result);
	}
	
	public void sortedOutput(int i)
	{
		if (i < 1) return;
		System.out.println(i + " letter words:");
		System.out.println();
		for (String s: result)
		{
			if (i == s.length()) System.out.println(s);
		}
		System.out.println();
		System.out.println();
		sortedOutput(i-1);
	}
}