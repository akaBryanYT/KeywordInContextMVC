package controller;

import java.util.List;

public class Alphabetizer {
	
	//Gives the indices where the new lines should be inserted into the storage
    public int[] getIndices(List<List<String>> allLines, List<List<String>> shiftedLines) {
        
    	//holds the final return indexes
    	int[] returnIndices = new int[shiftedLines.size()];
    	
    	
    	//Add each line into the main array
    	for(int i = 0; i < shiftedLines.size(); i++) {
    		String currLine = String.join(" ", shiftedLines.get(i));
    		
    		//insert into the proper spot
    		//find the index to insert it to first
    		boolean flag = true;
    		int j = 0;
    		while(flag && j < allLines.size()) {
    			String tempLine = String.join(" ", allLines.get(j));
    			
    			if(tempLine.compareTo(currLine) > 0) {
    				flag = false;
    				j--;
    			}
    			
    			j++;
    		}
    		
    		//add the line to the array
    		allLines.add(j, shiftedLines.get(i));
    		
    		returnIndices[i] = j;
    		
    		//Ensure that if there was a larger index, it is increased
    		for(int k = 0; k < i; k++) {
    			if(returnIndices[k] > j) {
    				returnIndices[k]++;
    			}
    		}
    		
    	}
    	
    	return returnIndices;
    	
    }
}
