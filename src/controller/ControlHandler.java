package controller;

import java.util.List;
import model.LineStorage;
import view.MainView;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import javax.swing.*;

//Handles the controller logic.
//The addLine method takes a line from the gui and adds it to the model
//Written by John Willy
public class ControlHandler {
	
	private int currLineID;
    private Alphabetizer alpha;
    private CircularShifter circ;
    private LineStorage storage;

    private MainView mainView;


    //Adds the line and its circularly shifted variants to the proper spot in the model
    public void addLines(List<List<String>> lines) {
        //TODO: Finish addLines method.
    	
    	//remove duplicates
    	List<List<String>> testLines = storage.getAll();
    	for(int i = lines.size() - 1; i >= 0; i--) {
    		if(testLines.contains(lines.get(i))) {
    			lines.remove(i);
    		}
    	}
    	
    	//add titles
    	for(List<String> line:lines) {
    		storage.addTitle(line);
    	}
    	
    	//Get the Ids for the lines
    	List<Integer> lineIDs = new ArrayList<Integer>();
    	for(int i = 0; i < lines.size(); i++) {
    		lineIDs.add(currLineID);
    		currLineID++;
    	}

        //First get the shifted lines based off of this line
    	DataHolder data = circ.circShift(lines, lineIDs);
        List<List<String>> shiftedLines = data.getLines();
        lineIDs = data.getIDs();

        //Get all of the stored lines from the model
        List<List<String>> allLines = storage.getAll();

        //Get the indexes that each line should be added as
        int[] indexes = alpha.getIndices(allLines, shiftedLines);
        
        System.out.println(shiftedLines);
        System.out.println(Arrays.toString(indexes));
        
        //Make sure that they are added with the smallest index going first
        for(int i = 0; i < shiftedLines.size(); i++) {
        	int tempMin = indexes[i];
        	int minIndex = i;
        	for(int j = i; j < shiftedLines.size(); j++) {
        		if(indexes[j] < tempMin) {
        			tempMin = indexes[j];
        			minIndex = j;
        		}
        	}
        	
        	//swap the values
        	indexes[minIndex] = indexes[i];
        	indexes[i] = tempMin;
        	Collections.swap(shiftedLines, i, minIndex);
        	Collections.swap(lineIDs,  i, minIndex);
        	
        }
        
        System.out.println(shiftedLines);
        System.out.println(Arrays.toString(indexes));
        
        
        //Add each line into the model
        for(int i = 0; i < indexes.length; i++) {
            storage.addLine(shiftedLines.get(i), indexes[i], lineIDs.get(i));
        }

        //Get the lines again
        allLines = storage.getAll();

        //Send them to the view
        mainView.setAllKWIC(allLines);
        mainView.setAllTitles(storage.getAllTitles());
        
    }


    public void deleteLines(List<List<String>> lines) {
    	
    	//delete title
    	for(List<String> line:lines){
    		storage.deleteTitle(line);
    	}
    	
        //TODO: Finish deleteLines method.
    	for(int i = 0; i < lines.size(); i++) {
    		storage.deleteLine(lines.get(i));
    	}
    	
    	//Send them to the view
        mainView.setAllKWIC(storage.getAll());
        mainView.setAllTitles(storage.getAllTitles());
    	
    }
    
    //Run by main method
    public void start(){
        // Create and show the GUI
        SwingUtilities.invokeLater(() -> this.mainView = new MainView(this));
        alpha = new Alphabetizer();
        circ = new CircularShifter();
        storage = new LineStorage();
        currLineID = 0;
    }
}