package model;

import java.util.List;
import java.util.Set;
import java.util.ArrayList;
import java.util.HashMap;

//Class acts as the database. It encapsulates the storage of the stored lines.
//It is also able to add a line at a specified index
//Written by John Willy
public class LineStorage {

    //Stores the lines
    private final List<List<String>> storedLines;
    
    private final List<List<String>> userLines;
    
    //Maps the lines to IDs
    private final HashMap<List<String>, Integer> hMap;

    //Initialize the data structure
    public LineStorage() {
        storedLines = new ArrayList<List<String>>();
        hMap = new HashMap<List<String>, Integer>();
        userLines = new ArrayList<List<String>>();
    }
    
    public void addTitle(List<String> line) {
    	userLines.add(line);
    }
    
    public void deleteTitle(List<String> line) {
    	userLines.remove(line);
    }
    
    public List<List<String>> getAllTitles() {
    	return userLines;
    }

    //Returns a copy of the stored lines
    public List<List<String>> getAll() {

        //Holds the copy
        List<List<String>> listCopy = new ArrayList<List<String>>();

        //Copy each line
        for(int i = 0; i < storedLines.size(); i++) {
            listCopy.add(new ArrayList<String>(storedLines.get(i)));
        }

        return listCopy;
    }

    //add a line at the specified index
    public void addLine(List<String> line, int index, int id) {
        storedLines.add(index, line);
        hMap.put(line, id);
    }
    
    //Delete a set of lines with the same ID as the line entered
    public void deleteLine(List<String> line) {
    	int lineID = hMap.get(line);
    	Object[] keys = hMap.keySet().toArray();
    	for (int i = keys.length -1; i >= 0; i--) {
    		  if(hMap.get(keys[i]) == lineID) {
    			  storedLines.remove(keys[i]);
    			  hMap.remove(keys[i]);
    		  }
    		}
    	
    }
}