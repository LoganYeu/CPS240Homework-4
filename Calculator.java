/*
 * CPS 240 Homework 4, Spring 2022
 * Calculator Design
 * Student Name:
 * Student Email:
 * Student ID:
 */

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class Calculator extends GUI {

    @Override // Override the start method in the Application class
    public void start(Stage primaryStage) {

        // create listeners for numeric  buttons
        // listeners set to observable
        // so when buttons are pressed/released, the listener is notified
        // resource: slide 7 page 19
        for(Button b: numberButtons){
            b.pressedProperty().addListener((observable, wasPressed, pressed) -> {
                // if button is pressed, change the color, get the text of the button, update display textfield
                if (pressed) {
                    // print debug info in terminal
                    System.out.println(b.getText());
                    // call buttonPress() in GUI.java to change the color of button
                    buttonPress(b);
                    // get current text from display field
                    String displayingText = operation.getText();
                    // add button text to display filed
                    displayingText = displayingText + b.getText();
                    // update displayText
                    setOperationText(displayingText);
                } else {
                    // button is released, call buttonRelease() in GUI.java to restore button's color
                    buttonRelease(b);
                }
            });
        }

        // call start() method in GUI.java to creat and show all GUIs
        super.start(primaryStage);
        primaryStage.setTitle("Calcualtor"); // Set the stage title
    }


    /**
     * The main method is only needed for the IDE with limited
     * JavaFX support. Not needed for running from the command line.
     */
    public static void main(String[] args) {
        launch(args);
    }
}
