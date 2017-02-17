package assignment3;

import java.util.ArrayList;
import java.util.Set;

public class DFS {
	ArrayList<String> dict;
	ArrayList<String> nodeVal;
	ArrayList<Integer> ladderMap;
	ArrayList<ArrayList<String>> unexploredList;
	
	DFS(Set<String> vertices) {
		dict = new ArrayList<String>(vertices);
		nodeVal = new ArrayList<String>();
		ladderMap = new ArrayList<Integer>();
		unexploredList = new ArrayList<ArrayList<String>>();
	}
	
	void genNeighbors(String node) {
		for (int l = 0; l < 5; l++) { //for each letter in five-letter word
			char[] word = node.toCharArray();
			for (int a = 0; a < 26; a++) { //for each possible letter
				word[l] = (char) (a + 65);
				String wordString = new String(word);
				if (wordString.equals(node) == false) {
					int index = dict.indexOf(wordString);
					if (index != -1) {
						unexploredList.get(unexploredList.size() - 1).add(dict.get(index));
						dict.remove(wordString);
					}
				}
			}
		}
	}
	
	boolean recursivesearch (String first, String last) {
		if (first.equals(last)) { // if node reaches desired word
			dict.remove(first);
			nodeVal.add(first);
			ladderMap.add(nodeVal.size()-1);
			return true;
		}
		else {
			dict.remove(first);
			int nodeIndex = nodeVal.size();
			nodeVal.add(first);
			unexploredList.add(new ArrayList<String>());
			genNeighbors(first);
			int nCount = unexploredList.get(nodeIndex).size();
	    	for (int n = 0; n < nCount; n++) {
	    		String neighbor = unexploredList.get(nodeIndex).get(n);
	    		if (recursivesearch(neighbor, last)) {
	    			ladderMap.add(nodeIndex);
	    			return true;
	    		}
	    	}
	    	return false;
		}		
	}
	
	
}
