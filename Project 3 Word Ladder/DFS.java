package assignment3;

import java.util.ArrayList;
import java.util.Set;

public class DFS {
	ArrayList<String> dict; // dictionary of unexplored nodes
	ArrayList<String> nodeVal; // DFS node visiting order
	ArrayList<String> ladder; //word ladder
	ArrayList<ArrayList<String>> neighborList; //list of neighbors for nodes
	
	DFS(Set<String> vertices) {
		dict = new ArrayList<String>(vertices);
		nodeVal = new ArrayList<String>();
		ladder = new ArrayList<String>();
		neighborList = new ArrayList<ArrayList<String>>();
	}
	
	void genNeighbors(String node) { //generates unexplored neighbors of node
		for (int l = 0; l < 5; l++) { //for each letter in five-letter word
			char[] word = node.toCharArray();
			for (int a = 0; a < 26; a++) { // try every word a letter away
				word[l] = (char) (a + 65);
				String wordString = new String(word);
				if (wordString.equals(node) == false) { // in closed neighborhood
					int index = dict.indexOf(wordString);
					if (index != -1) { // check if valid word
						neighborList.get(neighborList.size() - 1).add(dict.get(index));
						dict.remove(wordString);
					}
				}
			}
		}
	}
	
	boolean recursivesearch (String first, String last) { //start from last
		if (last.equals(first)) { // if node reaches desired word
			dict.remove(first);
			nodeVal.add(first);
			ladder.add(first); //start constructing word ladder
			return true;
		}
		else {
			dict.remove(last);
			int depth = nodeVal.size();
			nodeVal.add(last);
			neighborList.add(new ArrayList<String>()); //create list of unexplored neighbors for current node
			genNeighbors(last); //populate list
			int nCount = neighborList.get(depth).size();
	    	for (int n = 0; n < nCount; n++) { //explore unexplored neighbors
	    		String neighbor = neighborList.get(depth).get(n);
	    		if (recursivesearch(first, neighbor)) { // neighbor is in word ladder
	    			ladder.add(nodeVal.get(depth)); // add neighbor to word ladder
	    			return true;
	    		}
	    	}
	    	return false;
		}		
	}
	
	
}
