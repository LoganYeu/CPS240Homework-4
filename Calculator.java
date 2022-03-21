/*
 * CPS 240 Homework 4, Spring 2022
 * Calculator Design
 * Student Name:
 * Student Email:
 * Student ID:
 */

import java.math.BigDecimal;
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
        for(Button b : numberButtons){
            b.pressedProperty().addListener((observable, wasPressed, pressed) -> {
                // if button is pressed, change the color, get the text of the button, update display textfield
                if (pressed) {
                    if (justDidFullOperation) {
                        setOperationText("");
                        setResultText("");
                        justDidFullOperation = false;
                    }
                    // print debug info in terminal
                    System.out.println(b.getText());
                    // call buttonPress() in GUI.java to change the color of button
                    buttonPress(b);
                    // get current text from display field
                    String displayingText = operation.getText();
                    // add button text to display field
                    displayingText = displayingText + b.getText();
                    // update displayText
                    setOperationText(displayingText);
                } else {
                    // button is released, call buttonRelease() in GUI.java to restore button's color
                    buttonRelease(b);
                }
            });
        }

        for(Button b : operators){
            b.pressedProperty().addListener((observable, wasPressed, pressed) -> {
                // if button is pressed, change the color, get the text of the button, update display textfield
                // If statement below sets the display fields to empty when a button is pressed after an operation was finished prior.
                if (pressed) {
                    if (justDidFullOperation) {
                        setOperationText("");
                        setResultText("");
                        justDidFullOperation = false;
                    }
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

                    // Runs the equals method to finish an operation if the = button is pressed. Also checks for potential errors.
                    if (b.getText().equals("=")) {

                        try {
                            equals(displayingText);
                            // Dividing a double by 0 yields infinity instead of an error, accounting for this here and printing correct error message.
                        }
                        catch (ArithmeticException e) {
                            System.out.println("Invalid operation");
                            setResultText("Invalid operation");
                            justDidFullOperation = true;
                        }
                    }


                } else {
                    // button is released, call buttonRelease() in GUI.java to restore button's color
                    buttonRelease(b);
                }
            });
        }

        for(Button b : functionButtons){
            b.pressedProperty().addListener((observable, wasPressed, pressed) -> {
                // if button is pressed, change the color, get the text of the button, update display textfield
                if (pressed) {
                    // Completely clears the display fields if a new button is pressed after an operation or the C button is pressed.
                    if (justDidFullOperation || b.getText().equals("C")) {
                        setOperationText("");
                        setResultText("");
                        justDidFullOperation = false;
                    }

                    else if (b.getText().equals("DEL")) {
                        String[] currentOperationText = operation.getText().split("[+\\-\\u00d7\\u00f7]");
                        // Removes last element of string to give
                    }

                    else {
                        // print debug info in terminal
                        System.out.println(b.getText());
                        // call buttonPress() in GUI.java to change the color of button
                        buttonPress(b);
                        // get current text from display field
                        String displayingText = operation.getText();
                        // add button text to display field
                        displayingText = displayingText + b.getText();
                        // update displayText
                        setOperationText(displayingText);
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
    boolean justDidFullOperation = false;
    // Boolean to keep track of if a user just did a full instance of an operation in order to clear the calculator upon the next button press.
    public void equals(String currentOperation) {
        // Splits the currentOperation into 4 elements with elements 0-3 being [first number, operator, second number, =].
        // The regex in the line below keeps the +, -, \\u00d7, and \\u00f7 characters after splitting the string to help decide which operation is being done in the switch below.
        String[] parts = currentOperation.split("(?<=[+\\-\\u00d7\\u00f7\\=])|(?=[+\\-\\u00d7\\u00f7\\=])");
        String currentOperator = parts[1];
        Double firstNum = Double.parseDouble(parts[0]);
        Double secondNum = Double.parseDouble(parts[2]);

        // Switch case performs the specified math operation depending parts[1] which is the operator of the current math problem.
        double answer = 0;
        String stringAnswer = "";
        switch (currentOperator) {

            case "+":
                answer = firstNum + secondNum;
                System.out.println(answer);
                // Sets the setResultText text-field to answer WITHOUT the decimal places if the answer is a whole number.
                if ((answer % 1) == 0) {
                    setResultText(String.format("%.0f", answer));
                }
                // Sets the setResultText text-field to answer with 2 decimal places.
                else {
                    setResultText(String.format("%.2f", answer));
                }
                break;

            case "-":
                answer = firstNum - secondNum;
                System.out.println(answer);
                if ((answer % 1) == 0) {
                    setResultText(String.format("%.0f", answer));
                }
                // Sets the setResultText text-field to answer with 2 decimal places.
                else {
                    setResultText(String.format("%.2f", answer));
                }
                break;

            case "\u00d7":
                answer = firstNum * secondNum;
                System.out.println(answer);
                if ((answer % 1) == 0) {
                    setResultText(String.format("%.0f", answer));
                }
                // Sets the setResultText text-field to answer with 2 decimal places.
                else {
                    setResultText(String.format("%.2f", answer));
                }
                break;

            case "\u00f7":
                answer = firstNum / secondNum;
                // Dividing a double by 0 yields infinity instead of an error, accounting for this here by manually throwing the ArithmeticException.
                if (secondNum == 0) {
                    throw new ArithmeticException();
                }
                System.out.println(answer);
                if ((answer % 1) == 0) {
                    setResultText(String.format("%.0f", answer));
                }
                // Sets the setResultText text-field to answer with 2 decimal places.
                else {
                    setResultText(String.format("%.2f", answer));
                }
                break;

            default:
                stringAnswer = "Something went wrong.";
                System.out.println(stringAnswer);
                setResultText(stringAnswer);
        }
        justDidFullOperation = true;

    }

    /**
     * The main method is only needed for the IDE with limited
     * JavaFX support. Not needed for running from the command line.
     */
    public static void main(String[] args) {
        launch(args);
    }
}
