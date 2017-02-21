
package assignment3;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import org.junit.BeforeClass;
import org.junit.Test;
/**
 * This is the sample test cases for students
 * @author lisahua
 *
 */
public class WordLadderTester {
	private static Set<String> dict;
	private static ByteArrayOutputStream outContent;

	@BeforeClass
	public static void setUp() {
		Main.initialize();
		dict = Main.makeDictionary();
		outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
	}

	private boolean verifyLadder(ArrayList<String> ladder) {
		String prev = null;
		if (ladder == null)
			return true;
		for (String word : ladder) {
			if (!dict.contains(word.toUpperCase()) && !dict.contains(word.toLowerCase())) {
				return false;
			}
			if (prev != null && !differByOne(prev, word))
				return false;
			prev = word;
		}
		return true;
	}

	private static boolean differByOne(String s1, String s2) {
		if (s1.length() != s2.length())
			return false;

		int diff = 0;
		for (int i = 0; i < s1.length(); i++) {
			if (s1.charAt(i) != s2.charAt(i) && diff++ > 1) {
				return false;
			}
		}

		return true;
	}

	/** Has Word Ladder **/
	@Test(timeout = 30000)
	public void testBFS1() {
		ArrayList<String> res = Main.getWordLadderBFS("hello", "cells");

		if (res != null) {
			HashSet<String> set = new HashSet<String>(res);
			assertEquals(set.size(), res.size());
		}
		assertTrue(verifyLadder(res));
		assertFalse(res == null || res.size() == 0 || res.size() == 2);
		assertTrue(res.size() < 6);
	}

	@Test(timeout = 30000)
	public void testDFS1() {
		ArrayList<String> res = Main.getWordLadderDFS("hello", "cells");
		if (res != null) {
			HashSet<String> set = new HashSet<String>(res);
			assertEquals(set.size(), res.size());
		}
		assertTrue(verifyLadder(res));
		assertFalse(res == null || res.size() == 0 || res.size() == 2);

	}

	/** No Word Ladder **/
	@Test(timeout = 30000)
	public void testBFS2() {
		ArrayList<String> res = Main.getWordLadderBFS("aldol", "drawl");
		if (res != null) {
			HashSet<String> set = new HashSet<String>(res);
			assertEquals(set.size(), res.size());
		}
		assertTrue(res == null || res.size() == 0 || res.size() == 2);

	}

	@Test(timeout = 30000)
	public void testDFS2() {
		ArrayList<String> res = Main.getWordLadderDFS("aldol", "drawl");
		if (res != null) {
			HashSet<String> set = new HashSet<String>(res);
			assertEquals(set.size(), res.size());
		}
		assertTrue(res == null || res.size() == 0 || res.size() == 2);
	}

	@Test(timeout = 30000)
	public void testPrintLadder() {
		ArrayList<String> res = Main.getWordLadderBFS("twixt", "hakus");
		outContent.reset();
		Main.printLadder(res);
		String str = outContent.toString().replace("\n", "").replace(".", "").trim();
		assertEquals("no word ladder can be found between twixt and hakus", str);
		res = Main.getWordLadderBFS("hands", "hands");
		outContent.reset();
		Main.printLadder(res);
		str = outContent.toString().replace("\n", "").replace(".", "").replace("\r", "").trim();
		assertEquals("a 0-rung word ladder exists between hands and handshandshands", str);
	}

	@Test(timeout = 30000)
	public void testLongDFS(){
		ArrayList<String> res = Main.getWordLadderDFS("owner", "situp");
		if (res != null) {
			HashSet<String> set = new HashSet<String>(res);
			assertEquals(set.size(), res.size());
		}
		assertTrue(verifyLadder(res));
		assertFalse(res == null || res.size() == 0 || res.size() == 2);
		res = Main.getWordLadderDFS("molds", "heath");
		if (res != null) {
			HashSet<String> set = new HashSet<String>(res);
			assertEquals(set.size(), res.size());
		}
		assertTrue(verifyLadder(res));
		assertFalse(res == null || res.size() == 0 || res.size() == 2);
	}
	
	@Test(timeout = 30000)
	public void testLongBFS(){
		ArrayList<String> res = Main.getWordLadderBFS("dream", "meows");

		if (res != null) {
			HashSet<String> set = new HashSet<String>(res);
			assertEquals(set.size(), res.size());
		}
		assertTrue(verifyLadder(res));
		assertFalse(res == null || res.size() == 0 || res.size() == 2);
		assertFalse(res.size() < 11);
		res = Main.getWordLadderDFS("molds", "heath");
		if (res != null) {
			HashSet<String> set = new HashSet<String>(res);
			assertEquals(set.size(), res.size());
		}
		assertTrue(verifyLadder(res));
		assertFalse(res == null || res.size() == 0 || res.size() == 2);
	}

	@Test(timeout = 30000)
	public void testBFSQuit(){
		ArrayList<String> res = Main.getWordLadderBFS("/quit", "owner");
		assertTrue(res == null);
		res = Main.getWordLadderBFS("heath", "/quit");
		assertTrue(res == null);
		res = Main.getWordLadderBFS("/quit", "/quit");
		assertTrue(res == null);
	}

	@Test(timeout = 30000)
	public void testDFSQuit(){
		ArrayList<String> res = Main.getWordLadderDFS("/quit", "owner");
		assertTrue(res == null);
		res = Main.getWordLadderDFS("heath", "/quit");
		assertTrue(res == null);
		res = Main.getWordLadderDFS("/quit", "/quit");
		assertTrue(res == null);
	}
	@Test(timeout = 30000)
	public void testParse() {
		Scanner keyboard = new Scanner(System.in);
		ArrayList<String> res = Main.parse(keyboard); // enter "/quit"
		assertTrue(res.size() == 0);
		res = Main.parse(keyboard); // enter "/quit drags"
		assertTrue(res.size() == 0);
		res = Main.parse(keyboard); // enter "drags /quit"
		assertTrue(res.size() == 0);
		res = Main.parse(keyboard); // enter "/quit /quit"
		assertTrue(res.size() == 0);
		res = Main.parse(keyboard); // enter "money smart"
		assertTrue(res.size() == 2);
		assertEquals("money", res.get(0));
		assertEquals("smart", res.get(1));
		res = Main.parse(keyboard); // enter "smart money"
		assertTrue(res.size() == 2);
		assertEquals("smart", res.get(0));
		assertEquals("money", res.get(1));
	}

	@Test(timeout = 30000)
	public void testBFSEmpty(){
		ArrayList<String> res1 = Main.getWordLadderBFS("waver", "eerie");

		if (res1 != null) {
			HashSet<String> set = new HashSet<String>(res1);
			assertEquals(set.size(), res1.size());
		}
		assertTrue(res1 == null || res1.size() == 0 || res1.size() == 2);
		
	}
	
	@Test(timeout = 30000)
	public void testDFSEmpty(){
		ArrayList<String> res1 = Main.getWordLadderDFS("waver", "eerie");

		if (res1 != null) {
			HashSet<String> set = new HashSet<String>(res1);
			assertEquals(set.size(), res1.size());
		}
		assertTrue(res1 == null || res1.size() == 0 || res1.size() == 2);
		
	}
}
