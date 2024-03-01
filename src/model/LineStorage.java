package model;

import java.util.List;
import java.util.ArrayList;

//Class acts as the database. It encapsulates the storage of the stored lines.
//It is also able to add a line at a specified index
//Written by John Willy
public class LineStorage {

    //Stores the lines
    private final List<List<String>> storedLines;

    //Initialize the data structure
    public LineStorage() {
        storedLines = new ArrayList<List<String>>();
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
    public void addLine(List<String> line, int index) {
        storedLines.add(index, line);
    }
    
    //Delete a line at a specified index
    public void deleteLine(int index) {
    	storedLines.remove(index);
    }
}