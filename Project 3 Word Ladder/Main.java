/* WORD LADDER Main.java
 * EE422C Project 3 submission by
 * Ho-chang Chang
 * hc23882
 * 16220
 * Phyllis Ang
 * pya74
 * 16220
 * Slip days used: <0>
 * Git URL: https://github.com/erichcchang/EE422CProject3
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
					printLadder(ladderDFS);
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
		DFS programDFS = new DFS(dict);
		if (programDFS.recursivesearch(start.toUpperCase(), end.toUpperCase()) && programDFS.ladder.size() > 1) { // if word ladder exists
			return programDFS.ladder;
		}
		else {
			ArrayList<String> ladder = new ArrayList<String>();
			ladder.add(start);
			ladder.add(end);
			return ladder;
		}
	}
	
    public static ArrayList<String> getWordLadderBFS(String start, String end) {
    
		return null;
	}
    
	public static Set<String>  makeDictionary () {
		Set<String> words = new HashSet<String>();
		Scanner infile = null;
		try {
			infile = new Scanner (new File("five_letter_words.txt"));
			//infile = new Scanner (new File("C:/Users/ERICHC7/Documents/Eclipse/Project3/src/assignment3/five_letter_words.txt"));
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
	
	/**
	 * This function prints out the number of rungs in the ladder and the ladder itself if it exist
	 * Prints out that a word ladder cannot be found if no ladder exist 
	 * @param ladder to be displayed 
	 * 
	 */
	public static void printLadder(ArrayList<String> ladder) {
		int numWords = ladder.size()-2;
		String start = ladder.get(0);
		String end = ladder.get(ladder.size()-1);
		
		//if a word ladder cannot be found between them 
		if(start.equals(end) && !similarLetter(start, end)){
			System.out.println("no word ladder can be found between "+start+" and "+end+".");
		}
		//if a ladder exists between the words - print out the ladder
		else{
			System.out.println("a "+numWords+"-rung word ladder exists between "+start+" and "+end+".");
			for(int i = 0; i<ladder.size(); i++){
				System.out.println(ladder.get(i));
			}
		}	
	}


	/**
	 * This function checks to see if start and end differ from each other by one ladder
	 * @param start is the first word in the word ladder
	 * @param end is the last word in the word ladder
	 * @return boolean value of whether start and end differ from each other by one letter
	 */
	public static boolean similarLetter(String start, String end){
		char[] startChar = start.toCharArray();
		char[] endChar = end.toCharArray();
		int num_difference = 0; //keeps track of how many letters are similar between the two words 

		//compare each char in the word 
		for (int i = 0; i<startChar.length; i++){
			if(startChar[i] == endChar[i]){
				num_difference++;
			}
		}
		
		return num_difference == start.length()-1;
	}
}
