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

import java.util.ArrayList;
import java.util.Set;

public class BFS {

	/**
	 * The node class stores all the properties of each word
	 */
	static class Node{
		boolean visited; //keeps track of whether the node has been visited
		Node parent; //the node the word is connected to
		String word; //the actual word
		
		public Node(Node previous, String w){
			parent = previous;
			visited = false;
			word = w;
			
		}
		
		public Node(String w){
			this(null, w); 
		}
	}
	
	ArrayList<Node> queue; //keeps track of the frontier and what has been visited
	ArrayList<String> dictionary; //the dictionary that contains all the words
	
	public BFS(Set<String> dict){
		queue = new ArrayList<Node>();
		dictionary = new ArrayList<String>(dict);
	}
	
	/**
	 * The method returns all the words that differ from word by one letter
	 * @param word is the String where we have to find what words differ from it by one letter
	 * @return ArrayList of nodes that are word's neighbors
	 */
	public ArrayList<Node> getNeighbors(String word){
		//find all the words that differ from word by 1 letter 
		ArrayList<Node> neighbor = new ArrayList<Node>(); //store all of word's neighbors


		for (int i = 0; i<word.length(); i++){
			//change the word into a char array so each char can be changed
			char[] wordChar = word.toCharArray();
			char changingChar = wordChar[i]; //keep track of the current letter in that position 

			//changes that letter to all possible letter in the alphabet
			for(int j = 0; j < 26; j++){
				char replacement = (char)(j+65);

				//change the word if it is not the same as the original char
				if(changingChar!=replacement){
					wordChar[i] = replacement;
					String wordString = new String(wordChar);

					//add to neighbor list if it is part of the dictionary
					if(dictionary.indexOf(wordString)!=-1){
						Node nodeNeighbor = new Node(wordString);
						neighbor.add(nodeNeighbor);
						dictionary.remove(wordString);
					}
				}
			}
		}
		return neighbor;
	}
	

	/**
	 * This method searches through the shortest path betweeen start and end
	 * @param start is the starting word of the ladder
	 * @param end is the last word in the ladder
	 * @return the node that the end word is stored in 
	 */
	public Node BFS_Search(String start, String end){
		String current; //keeps track of the current word in the node 

		//construct and add the root node - start
		Node first = new Node(start);
		queue.add(first);
		dictionary.remove(first.word);

		//keep traversing the queue as long as there is a node to visit 
		while(!queue.isEmpty()){
			current = queue.get(0).word;

			//stop searching if end is found 
			if(current.equals(end)){
				return queue.get(0);
			}

			//get current node's neighbor
			else{
				ArrayList<Node> neighbor = getNeighbors(current);

				//if current node has no neighbors then no ladder exist
				if(neighbor == null){
					return null;
				}

				for(int i = 0; i<neighbor.size(); i++){
					//check if each neighbor has been visited 
					if(!neighbor.get(i).visited){
						neighbor.get(i).visited = true;
						neighbor.get(i).parent=queue.get(0);
						queue.add(neighbor.get(i));
					}
				}
			}
			queue.remove(0);
		}
		return null;
	}
	
	/**
	 * The method traverses through the parent nodes to construct the ladder
	 * @param start is the starting word of the ladder
	 * @param end is the last word in the ladder
	 * @return ArrayList of the ladder between start and end 
	 */
	public ArrayList<String> getShortestPath(String start, String end){
		ArrayList<String> list = new ArrayList<String>();
		Node last = BFS_Search(start, end); //search for the node that end was stored in

		//traverse through parent nodes to construct list if the last node exist
		if(last != null){
			Node currentNode = last;

			//keep constructing list as long as start node has not been encountered 
			while(currentNode.parent != null){
				list.add(0, currentNode.word);
				currentNode = currentNode.parent;
			}
			list.add(0, start);
			
		}

		//if no last node exist add the start and end word to the list
		else{
			list.add(start);
			list.add(end);
		}
		if (list.size() == 1) { // if start == end
			list.add(end);
		}
		return list;
	}
}
