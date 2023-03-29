package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class GUI{
    private static JTextField inputTextField;
    private static JTextArea outputTextArea;

    public static void main(String[] args) {
        JFrame frame = new JFrame("GUI");

        //todo Set the size of the window
        frame.setSize(600, 400);

        frame.setLocationRelativeTo(null);

        inputTextField = new JTextField();
        inputTextField.setPreferredSize(new Dimension(400, 30)); // set preferred size

        frame.add(inputTextField);

        JButton button = new JButton("Enter");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (outputTextArea == null) {
                    outputTextArea = new JTextArea();
                    outputTextArea.setEditable(false);
                }

                //todo Get the text entered by the user
                String newText = inputTextField.getText();
                String currentOutputText = outputTextArea.getText();
                String updatedOutputText = currentOutputText + "\n" + newText;
                outputTextArea.setText(updatedOutputText);

                //todo Clear the text field
                inputTextField.setText("");
            }
        });

        frame.add(button);

        //todo Create a new button object for the "Save" button
        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int option = fileChooser.showSaveDialog(frame);

                if (option == JFileChooser.APPROVE_OPTION) {
                    File file = fileChooser.getSelectedFile();
                    try {
                        BufferedWriter writer = new BufferedWriter(new FileWriter(file));
                        writer.write(outputTextArea.getText());
                        writer.close();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });

        //todo Add the save button to the frame
        frame.add(saveButton);

        //todo Create a new JButton object for the "Load" button
        JButton loadButton = new JButton("Load");
        loadButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int option = fileChooser.showOpenDialog(frame);

                if (option == JFileChooser.APPROVE_OPTION) {
                    File file = fileChooser.getSelectedFile();
                    try {
                        BufferedReader reader = new BufferedReader(new FileReader(file));
                        String line;
                        StringBuilder text = new StringBuilder();
                        while ((line = reader.readLine()) != null) {
                            text.append(line).append("\n");
                        }
                        reader.close();
                        outputTextArea.setText(text.toString());
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });

        frame.add(loadButton);

        frame.setLayout(new FlowLayout());

        frame.setVisible(true);

        JFrame outputFrame = new JFrame("Output Window");
        outputFrame.setSize(600, 400);
        outputFrame.setLocation(frame.getX() + frame.getWidth(), frame.getY());

        outputTextArea = new JTextArea();
        outputTextArea.setEditable(false);
        outputTextArea.setPreferredSize(new Dimension(400, 300)); // set preferred size

        JScrollPane scrollPane = new JScrollPane(outputTextArea);
        outputFrame.add(scrollPane);

        outputFrame.setVisible(true);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        outputFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        inputTextField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                button.doClick();
            }
        });
    }
}