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

        for(Button b: operators){
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
                    if (b.getText().equals("=")) {
                        equals(displayingText);
                    }
                } else {
                    // button is released, call buttonRelease() in GUI.java to restore button's color
                    buttonRelease(b);
                }
            });
        }



        // call start() method in GUI.java to create and show all GUIs
        super.start(primaryStage);
        primaryStage.setTitle("Calculator"); // Set the stage title
    }

    public void equals(String currentOperation) {
        // Splits the currentOperation on operator characters while also keeping the characters in the resulting array.
        String[] parts = currentOperation.split("(?<=[+\\-\\u00d7\\u00f7\\=])|(?=[+\\-\\u00d7\\u00f7\\=])");
        String currentOperator = parts[1];
        int firstNum = Integer.parseInt(parts[0]);
        int secondNum = Integer.parseInt(parts[2]);

        int answer = 0;
        String stringAnswer = "";
        switch (currentOperator) {

            case "+":
                answer = firstNum + secondNum;
                System.out.println(answer);
                stringAnswer = Integer.toString(answer);
                setResultText(stringAnswer);
                break;

            case "-":
                answer = firstNum - secondNum;
                System.out.println(answer);
                stringAnswer = Integer.toString(answer);
                setResultText(stringAnswer);
                break;

            case "\u00d7":
                answer = firstNum * secondNum;
                System.out.println(answer);
                stringAnswer = Integer.toString(answer);
                setResultText(stringAnswer);
                break;

            case "\u00f7":
                answer = firstNum / secondNum;
                System.out.println(answer);
                stringAnswer = Integer.toString(answer);
                setResultText(stringAnswer);
                break;

            default:
                stringAnswer = "Something went wrong.";
                System.out.println(stringAnswer);
                setResultText(stringAnswer);
        }



    }

    /**
     * The main method is only needed for the IDE with limited
     * JavaFX support. Not needed for running from the command line.
     */
    public static void main(String[] args) {
        launch(args);
    }
}
