package controller;

import java.util.List;
import model.LineStorage;
import view.MainView;

import javax.swing.*;

//Handles the controller logic.
//The addLine method takes a line from the gui and adds it to the model
public class ControlHandler {

    private Alphabetizer alpha;
    private CircularShifter circ;
    private LineStorage storage;

    private MainView mainView;

    //Constructor initializes the class attributes
    public ControlHandler() {

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

    public void start(){
        // Create and show the GUI
        SwingUtilities.invokeLater(() -> new MainView(this));
        alpha = new Alphabetizer();
        circ = new CircularShifter();
        storage = new LineStorage();
    }

    public void addLines(List<List<String>> parsedLines) {
    }

    public void deleteLines(List<List<String>> linesToBeDeleted){

    }
}