package view;

import controller.ControlHandler;

import javax.swing.*;

import com.formdev.flatlaf.*;

import java.awt.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.*;
import java.util.List;

/**
 * @author Bryan Chavez
 */
public class MainView {
    private JPanel contentPane;
    private JTextArea kwicTextBox;
    private JTextArea userTextBox;
    private JList<String> allTitlesBox;
    private JButton submitTextButton;
    private JButton deleteTitlesButton;
    private JFrame mainFrame;
    private final ControlHandler controller;

    public MainView(ControlHandler controller){
        this.mainFrame = new JFrame();
        this.controller = controller;

        createMainView();
    }

    private void createMainView() {
        //create the main frame
        mainFrame = new JFrame("Main View");
            mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            mainFrame.setPreferredSize(new Dimension(900, 675)); //set initial size (you can adjust this)

        FlatDarculaLaf.setup();

        //create content pane with BorderLayout
        contentPane = new JPanel(new GridBagLayout());

        //Grid bag constraints
        GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(5, 5, 5, 5); // Add some padding between components

        //fonts
        Font fontText = new Font("Calibri", Font.PLAIN, 16);
        Font fontLabel = new Font("Calibri", Font.BOLD, 14);

        //color
        Color textGrey = new Color(70, 73, 75);

        //create text area for input and display
        kwicTextBox = new JTextArea();
            kwicTextBox.setEditable(false); //set kwic text box non-editable
            kwicTextBox.setFont(fontText);
            kwicTextBox.setBackground(textGrey);

        userTextBox = new JTextArea();
            userTextBox.setFont(fontText);
            userTextBox.setBackground(textGrey);

        allTitlesBox = new JList<>();
            allTitlesBox.setFont(fontText);
            allTitlesBox.setBackground(textGrey);

        //create Buttons
        submitTextButton = new JButton();
            submitTextButton.setText("Submit Titles");
            submitTextButton.setFont(fontText);

            //add action listen to submit text button to send controller the new lines, and also clear text box
            submitTextButton.addActionListener(e -> {
                //retrieve text from userTextBox
                String userInput = userTextBox.getText();

                //parse text into List<List<String>>
                List<List<String>> parsedLines = parseLines(userInput);

                //validate lines
                parsedLines = removeDuplicateLines(parsedLines);

                //call controller to add new lines
//                    controller.addLines(parsedLines);

                //clear userTextBox
                userTextBox.setText("");

                setAllTitles(parsedLines);
            });

        deleteTitlesButton = new JButton();
            deleteTitlesButton.setText("Delete Titles");
            deleteTitlesButton.setFont(fontText);

            //add action listen to submit text button to send controller the new lines, and also clear text box
            deleteTitlesButton.addActionListener(e -> {
                List<List<String>> deletedLines = new ArrayList<>();
                List<String> selected = allTitlesBox.getSelectedValuesList();
                if(selected.isEmpty()) return;

                for(String line : selected){
                    String[] delimitedLine = line.split("\\s+");
                    deletedLines.add(Arrays.asList(delimitedLine));
                }

                System.out.println(deletedLines);

                //call controller to add new lines
                controller.deleteLines(deletedLines);
            });

            //add action listener to delete any selected lines, sent it to the controller to update the model,
            // and update the view

        //create labels
        JLabel kwicTextBoxLabel = new JLabel("KWIC");
            kwicTextBoxLabel.setFont(fontLabel);

        JLabel userTextBoxLabel = new JLabel("Enter Titles Here");
            userTextBoxLabel.setFont(fontLabel);

        JLabel allTitlesLabel = new JLabel("Your Titles");
            allTitlesLabel.setFont(fontLabel);

        //add components to each of their respective panes
        JPanel leftPanel = new JPanel(new GridBagLayout());
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.fill = GridBagConstraints.BOTH;
            gbc.weightx = 1;
            leftPanel.add(kwicTextBoxLabel, gbc);

            gbc.gridy = 1;
            gbc.fill = GridBagConstraints.BOTH;
            gbc.weightx = 1;
            gbc.weighty = 1;
            leftPanel.add(kwicTextBox, gbc);

        JPanel middlePanel = new JPanel(new GridBagLayout());
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.fill = GridBagConstraints.BOTH;
            gbc.weightx = 1;
            gbc.weighty = 0;
            middlePanel.add(userTextBoxLabel, gbc);

            gbc.gridy = 1;
            gbc.gridheight = 4;
            gbc.fill = GridBagConstraints.BOTH;
            gbc.weightx = 1;
            gbc.weighty = 0.95;
            middlePanel.add(userTextBox, gbc);

            gbc.gridy = 5;
            gbc.gridheight = 1;
            gbc.fill = GridBagConstraints.BOTH;
            gbc.weightx = 1;
            gbc.weighty = 0.05;
            middlePanel.add(submitTextButton, gbc);

        JPanel rightPanel = new JPanel(new GridBagLayout());
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.fill = GridBagConstraints.BOTH;
            gbc.weightx = 1;
            gbc.weighty = 0;
            rightPanel.add(allTitlesLabel, gbc);

            gbc.gridy = 1;
            gbc.gridheight = 4;
            gbc.fill = GridBagConstraints.BOTH;
            gbc.weightx = 1;
            gbc.weighty = 0.95;
            rightPanel.add(allTitlesBox, gbc);

            gbc.gridy = 5;
            gbc.gridheight = 1;
            gbc.fill = GridBagConstraints.BOTH;
            gbc.weightx = 1;
            gbc.weighty = 0.05;
            rightPanel.add(deleteTitlesButton, gbc);

        // Add panels to the content pane
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1;
        gbc.weighty = 1;
        contentPane.add(leftPanel, gbc);

        gbc.gridx = 1;
        gbc.gridheight = 2;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1;
        gbc.weighty = 0;
        contentPane.add(middlePanel, gbc);

        gbc.gridx = 2;
        gbc.gridheight = 2;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1;
        gbc.weighty = 0;
        contentPane.add(rightPanel, gbc);

        //set content pane to main frame
        mainFrame.setContentPane(contentPane);

        //display our view
        mainFrame.pack();
        mainFrame.setLocationRelativeTo(null); //center the frame on the screen
        mainFrame.setVisible(true);
    }

    private List<List<String>> removeDuplicateLines(List<List<String>> lines){
        HashSet<List<String>> dedupedLines = new LinkedHashSet<>(lines);
        return new ArrayList<>(dedupedLines);
    }

    //method to parse text into List<List<String>>
    private List<List<String>> parseLines(String text) {
        List<List<String>> lines = new ArrayList<>();

        //split text into lines
        String[] lineArray = text.split("\\n");

        // Iterate through each line
        for (String line : lineArray) {
            //make sure line isnt empty or only whitespace
            if(line.isBlank()) continue;

            // Split line by space and add to list
            List<String> words = Arrays.asList(line.split("\\s+"));
            lines.add(words);
        }

        return lines;
    }

    //method to format List<List<String>> into text with lines separated by spaces and newlines
    private String formatLines(List<List<String>> lines) {
        StringBuilder formattedText = new StringBuilder();

        //go through each line
        for (List<String> line : lines) {
            //add each word in the line separated by spaces
            for(int i = 0; i<line.size(); i++){
                formattedText.append(line.get(i));
                if(i!=line.size()-1){
                    formattedText.append(" ");
                }
            }
            // Append newline at the end of the line
            formattedText.append("\n");
        }

        return formattedText.toString();
    }

    // add all titles to titles box.
    public void setAllTitles(List<List<String>> allTitles){
        String[] strings = new String[allTitles.size()];
        for(int i = 0; i<allTitles.size(); i++){
            List<String> line = allTitles.get(i);
            StringBuilder lineString = new StringBuilder();
            for(int j = 0; j<line.size(); j++){
                lineString.append(line.get(j));
                if(j!=line.size()-1){
                    lineString.append(" ");
                }
            }
            strings[i] = lineString.toString();
        }
        allTitlesBox.setListData(strings);
    }

}