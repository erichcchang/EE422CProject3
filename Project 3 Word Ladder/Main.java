/* WORD LADDER Main.java
 * EE422C Project 3 submission by
 * Ho-chang Chang
 * hc23882
 * 16220
 * <Student2 Name>
 * <Student2 EID>
 * <Student2 5-digit Unique No.>
 * Slip days used: <0>
 * Git URL:
 * Spring 2017
 */


package assignment3;
import java.util.*;
import java.io.*;

public class Main {
	
	static Set<String> dict;
	
	public static void main(String[] args) throws Exception {
		
		Scanner kb;	// input Scanner for commands
		PrintStream ps;	// output file
		// If arguments are specified, read/write from/to files instead of Std IO.
		if (args.length != 0) {
			kb = new Scanner(new File(args[0]));
			ps = new PrintStream(new File(args[1]));
			System.setOut(ps);			// redirect output to ps
		} else {
			kb = new Scanner(System.in);// default from Stdin
			ps = System.out;			// default to Stdout
		}
		initialize();
		ArrayList<String> words = parse(kb);
		while (words.size() != 0) {
			if (words.size() == 2) {
				String start = words.get(0);
				String end = words.get(1);
				if (dict.contains(start.toUpperCase()) == true && dict.contains(end.toUpperCase()) == true) {
					ArrayList<String> ladderDFS = getWordLadderDFS(start, end);
					//ArrayList<String> ladderBFS = getWordLadderBFS(start, end);
					//printLadder(ladderDFS);
					//printLadder(ladderBFS);
				}
			}
			words = parse(kb);
		}
	}
	
	public static void initialize() {
		dict = makeDictionary();
	}
	
	/**
	 * @param keyboard Scanner connected to System.in
	 * @return ArrayList of 2 Strings containing start word and end word. 
	 * If command is /quit, return empty ArrayList. 
	 */
	public static ArrayList<String> parse(Scanner keyboard) {
		ArrayList<String> input = new ArrayList<String>();
		String word = keyboard.next();
		if (word.equals("/quit") == false) {
			input.add(word);
			word = keyboard.next();
			input.add(word);
		}
		return input;
	}
	
	public static ArrayList<String> getWordLadderDFS(String start, String end) {
		ArrayList<String> ladder = new ArrayList<String>();
		DFS programDFS = new DFS(dict);
		if (programDFS.recursivesearch(start.toUpperCase(), end.toUpperCase())) {
			for (int i = programDFS.ladderMap.size() - 1; i >= 0; i--) {
				ladder.add(programDFS.nodeVal.get(programDFS.ladderMap.get(i)));
			}
		}
		return ladder;
	}
	
    public static ArrayList<String> getWordLadderBFS(String start, String end) {
    
		return null;
	}
    
	public static Set<String>  makeDictionary () {
		Set<String> words = new HashSet<String>();
		Scanner infile = null;
		try {
			// infile = new Scanner (new File("five_letter_words.txt"));
			infile = new Scanner (new File("C:/Users/ERICHC7/Documents/Eclipse/Project3/src/assignment3/five_letter_words.txt"));
		} catch (FileNotFoundException e) {
			System.out.println("Dictionary File not Found!");
			e.printStackTrace();
			System.exit(1);
		}
		while (infile.hasNext()) {
			words.add(infile.next().toUpperCase());
		}
		return words;
	}
	
	public static void printLadder(ArrayList<String> ladder) {
		
	}
	// TODO
	// Other private static methods here
}
