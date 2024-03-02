package controller;


import java.util.List;

//Class to hold data returned by the circular shifter
//Written by John Willy
public class DataHolder {
	
	private final List<List<String>> lines;
	private final List<Integer> IDs;
	
	public DataHolder(List<List<String>> lines, List<Integer> IDs) {
		this.lines = lines;
		this.IDs = IDs;
		
	}
	
	public List<List<String>> getLines(){
		return lines;
	}
	
	public List<Integer> getIDs(){
		return IDs;
	}

}
