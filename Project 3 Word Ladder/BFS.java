package assignment3;

import java.util.ArrayList;
import java.util.Set;

public class BFS {
	static class Node{
		boolean visited;
		Node parent;
		String word;
		
		public Node(Node previous, String w){
			parent = previous;
			visited = false;
			word = w;
			
		}
		
		public Node(String w){
			this(null, w); 
		}
	}
	
	ArrayList<Node> queue;
	ArrayList<String> dictionary;
	
	public BFS(Set<String> dict){
		queue = new ArrayList<Node>();
		dictionary = new ArrayList<String>(dict);
	}
	
	public ArrayList<Node> getNeighbors(String word){
		//find all the words that differ from word by 1 letter 
		ArrayList<Node> neighbor = new ArrayList<Node>(); //store all of word's neighbors
		for (int i = 0; i<word.length(); i++){
			char[] wordChar = word.toCharArray();
			char changingChar = wordChar[i];
			for(int j = 0; j < 26; j++){
				char replacement = (char)(j+65);
				if(changingChar!=replacement){
					wordChar[i] = replacement;
					String wordString = new String(wordChar);
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
	
	public Node BFS_Search(String start, String end){
		String current;
		Node first = new Node(start);
		queue.add(first);
		dictionary.remove(first.word);
		//int index = 0;
		while(!queue.isEmpty()){
			current = queue.get(0).word;
			if(current.equals(end)){
				return queue.get(0);
			}
			else{
				ArrayList<Node> neighbor = getNeighbors(current);
				for(int i = 0; i<neighbor.size(); i++){
					if(!neighbor.get(i).visited){
						neighbor.get(i).visited = true;
						neighbor.get(i).parent=queue.get(0);
						queue.add(neighbor.get(i));
					}
				}
				//for all neighbors 
				//if neighbor not visited before
					//mark visited
					//mark neighbor.parent = current
					//add neighbor to queue
			}
			queue.remove(0);
		}
		return null;
	}
	
	public ArrayList<String> getShortestPath(String start, String end){
		ArrayList<String> list = new ArrayList<String>();
		Node last = BFS_Search(start, end);
		if(last != null){
			Node currentNode = last;
			while(currentNode.parent != null){
				list.add(0, currentNode.word);
				currentNode = currentNode.parent;
			}
			list.add(0, start);
			
		}
		else{
			list.add(start);
			list.add(end);
		}
		return list;
	}
}
