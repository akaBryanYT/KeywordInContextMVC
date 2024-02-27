package controller;

import java.util.List;
import model.LineStorage;

//Handles the controller logic.
//The addLine method takes a line from the gui and adds it to the model
public class ControlHandler {

    private Alphabetizer alpha;
    private CircularShifter circ;
    private LineStorage storage;

    //Constructor initializes the class attributes
    public ControlHandler(LineStorage s) {
        alpha = new Alphabetizer();
        circ = new CircularShifter();
        storage = s;
    }

    //Adds the line and its circularly shifted variants to the proper spot in the model
    public void addLine(List<String> line) {

        //First get the shifted lines based off of this line
        List<List<String>> shiftedLines = circ.circShift(line);

        //Get all of the stored lines from the model
        List<List<String>> allLines = storage.getAll();

        //Get the indexes that each line should be added as
        int[] indexes = alpha.getIndices(allLines, shiftedLines);

        //Add each line into the model
        for(int i = 0; i < indexes.length; i++) {
            storage.addLine(shiftedLines.get(i), indexes[i]);
        }

    }

}