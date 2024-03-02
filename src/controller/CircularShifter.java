package controller;

import java.util.ArrayList;
import java.util.List;

//Class to circularly shift the lines. Written by John Willy.
//Code mostly taken from our assignment one
public class CircularShifter {
    public DataHolder circShift(List<List<String>> inputLines, List<Integer> lineIDs) {
    	
    	//original list size needed for loop
		int oldSize = inputLines.size();
		
		//Loop through each line in the list
		for(int i = 0; i < oldSize; i++) {
			
			List<String> currLine = inputLines.get(i);
			
			//Create one new line for each word (except the first)
			for(int j = 1; j < currLine.size(); j++) {
				
				ArrayList<String> newLine = new ArrayList<String>();
				
				//Add each word to the new line
				int k = j;
				do {
					
					//Add word to new line
					newLine.add(currLine.get(k));
					
					//Increment k
					k = (k+1) % currLine.size();
					
					
				}while(k != j);
				
				//Add new line to the list
				inputLines.add(newLine);
				
				//Add the corresponding ID
				lineIDs.add(lineIDs.get(i));
				
			}
			
		}
		
		//Return the new set of lines
		return new DataHolder(inputLines, lineIDs);
    }
}
