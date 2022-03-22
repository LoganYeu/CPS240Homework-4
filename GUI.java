/*
 * CPS 240 Homework 4, Spring 2022
 * Calculator Design
 * Student Name: Logan Yeubanks
 * Student Email: yeuba1lm@cmich.edu
 * Student ID: 823030
 */

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.shape.Line;

public class GUI extends Application {

    /* declare number button */
    protected Button[] numberButtons = {new Button("0"), new Button("1"), new Button("2"), new Button("3"), new Button("4"), new Button("5"), new Button("6"), new Button("7"), new Button("8"), new Button("9"), new Button(".")};

    /* declare operator button
     * Unicode list: \u00d7: MUL \u00f7: DIV */
    protected Button[] operators = {new Button("="), new Button("+"), new Button("-"), new Button("\u00d7"), new Button("\u00f7")};

    protected Button[] functionButtons = {new Button("C"), new Button("DEL"), new Button("%")};


    /* declare result and operation textField */
    protected TextField result = new TextField();
    protected TextField operation = new TextField();


    @Override // Override the start method in the Application class
    public void start(Stage primaryStage) {

        // set number button size, color, background, text color, text size
        for(Button b : numberButtons){
            // set size
            if(b.getText() == "0")
                b.setPrefSize(140,70);
            else
                b.setPrefSize(70,70);
            // set color, fontsize, etc. in css style
            b.setStyle("-fx-border-color: #525050; -fx-border-width: 1px; -fx-background-color: #858282; -fx-font-size: 2em; -fx-text-fill: #ffffff;");
        }

        // set operator button size, color, background, text color, text size
        for (Button b : operators){
            // set size
            b.setPrefSize(70,70);
            b.setStyle("-fx-border-color: #525050; -fx-border-width: 1px; -fx-background-color: #c49c16; -fx-font-size: 2em; -fx-text-fill: #ffffff;");
        }

        int functionButtonCount = 0;
        for (Button b : functionButtons) {
            b.setPrefSize(70, 70);
            if (functionButtonCount == 1) {
                b.setStyle("-fx-border-color: #525050; -fx-border-width: 1px; -fx-background-color: #565656; -fx-font-size: 1.5em; -fx-text-fill: #ffffff;");
            }
            else {
                b.setStyle("-fx-border-color: #525050; -fx-border-width: 1px; -fx-background-color: #565656; -fx-font-size: 2em; -fx-text-fill: #ffffff;");
            }
            functionButtonCount++;
        }



        // Create a pane for middle buttons
        GridPane paneMiddle = new GridPane();
        // Set center alignment, Pos.CENTER
        paneMiddle.setAlignment(Pos.CENTER);
        // set background color
        paneMiddle.setStyle("-fx-background-color: #525050;");

        // adds the clear, delete, and % operator buttons in that order

        paneMiddle.add(functionButtons[0], 0, 0);
        paneMiddle.add(functionButtons[1], 1, 0);
        paneMiddle.add(functionButtons[2], 2, 0);


        // add div operator button
        paneMiddle.add(operators[4], 3, 0); // "/"
        // add number 7, 8, 9 buttons
        paneMiddle.add(numberButtons[7], 0, 1);
        paneMiddle.add(numberButtons[8], 1, 1);
        paneMiddle.add(numberButtons[9], 2, 1);
        // add mul operator button
        paneMiddle.add(operators[3], 3, 1); // "*"
        // add number 4, 5, 6 buttons
        paneMiddle.add(numberButtons[4], 0, 2);
        paneMiddle.add(numberButtons[5], 1, 2);
        paneMiddle.add(numberButtons[6], 2, 2);
        // add sub operator button
        paneMiddle.add(operators[2], 3, 2); // "-"
        // add number 1, 2, 3 buttons
        paneMiddle.add(numberButtons[1], 0, 3);
        paneMiddle.add(numberButtons[2], 1, 3);
        paneMiddle.add(numberButtons[3], 2, 3);
        // add add operator button
        paneMiddle.add(operators[1], 3, 3); // "+"

        // Create pane1 for lower part
        GridPane paneBottom = new GridPane();
        // Set center alignment, Pos.CENTER
        paneBottom.setAlignment(Pos.CENTER);
        // set background color
        paneBottom.setStyle("-fx-background-color: #525050;");
        // add number 0 button
        paneBottom.add(numberButtons[0], 0, 0);
        // add dot button
        paneBottom.add(numberButtons[10], 1, 0);
        // add assign button
        paneBottom.add(operators[0], 2, 0); // "="

        // set display textfiled
        operation.setAlignment(Pos.BOTTOM_RIGHT);
        // set display textfiled non-editable
        operation.setEditable(false);
        // set display size, matching the windows size
        operation.setPrefWidth(280);
        operation.setPrefHeight(48);
        // set display backgroup, matching the app style
        operation.setStyle("-fx-border-color: #525050; -fx-border-width: 1px; -fx-background-color: #525050; -fx-font-size: 2.5em; -fx-text-fill: #ffffff;");
        // create a pane for display textfiled
        Pane paneForOperation = new Pane();
        paneForOperation.setPrefSize(280, 50);
        // add display textfiled to pane
        paneForOperation.getChildren().add(operation);

        // set result textfield
        result.setAlignment(Pos.BOTTOM_RIGHT);
        // set result textfiled non-editable
        result.setEditable(false);
        result.setPrefWidth(280);
        result.setPrefHeight(48);
        result.setStyle("-fx-border-color: #525050; -fx-border-width: 1px; -fx-background-color: #525050; -fx-font-size: 2.5em; -fx-text-fill: #ffffff;");
        Pane paneForResult = new Pane();
        paneForResult.setPrefSize(280, 50);
        paneForResult.getChildren().add(result);

        // create a Hbox
        VBox vBox = new VBox();
        vBox.setStyle("-fx-background-color: #525050;");
        vBox.getChildren().add(paneForOperation);
        vBox.getChildren().add(paneForResult);
        vBox.getChildren().add(paneMiddle);
        vBox.getChildren().add(paneBottom);

        // Create a scene and place it in the stage
        Scene scene = new Scene(vBox);
        primaryStage.setTitle("Calculator"); // Set the stage title
        primaryStage.setResizable(false); // disable resize
        primaryStage.setScene(scene); // Place the scene in the stage
        primaryStage.show(); // Display the stage
    }

    /* change button color when button is pressed */
    public void buttonPress(Button b) {
        if (b.getText().equals("DEL")) {
            b.setStyle("-fx-border-color: #525050; -fx-border-width: 1px; -fx-background-color: #aba7a7; -fx-font-size: 1.5em; -fx-text-fill: #ffffff;");
        }
        else {
            b.setStyle("-fx-border-color: #525050; -fx-border-width: 1px; -fx-background-color: #aba7a7; -fx-font-size: 2em; -fx-text-fill: #ffffff;");
        }

    }

    /* restore button color when button is release */
    public void buttonRelease(Button b) {
        if(b.getText() == "0" || b.getText() == "1" || b.getText() == "2" || b.getText() == "3" || b.getText() == "4" || b.getText() == "5" || b.getText() == "6" || b.getText() == "7" || b.getText() == "8" || b.getText() == "9" ||b .getText() == ".")
            b.setStyle("-fx-border-color: #525050; -fx-border-width: 1px; -fx-background-color: #858282; -fx-font-size: 2em; -fx-text-fill: #ffffff;");
        else if(b.getText() == "+" || b.getText() == "-" || b.getText() == "\u00d7" || b.getText().equals("\u00f7") || b.getText() == "=")
            b.setStyle("-fx-border-color: #525050; -fx-border-width: 1px; -fx-background-color: #c49c16; -fx-font-size: 2em; -fx-text-fill: #ffffff;");
        else if (b.getText().equals("DEL")) {
            b.setStyle("-fx-border-color: #525050; -fx-border-width: 1px; -fx-background-color: #565656; -fx-font-size: 1.5em; -fx-text-fill: #ffffff;");
        }
        else if (b.getText().equals("C") || b.getText().equals("%")) {
            b.setStyle("-fx-border-color: #525050; -fx-border-width: 1px; -fx-background-color: #565656; -fx-font-size: 2em; -fx-text-fill: #ffffff;");
        }
        else
            b.setStyle("-fx-border-color: #525050; -fx-border-width: 1px; -fx-background-color: #615f5f; -fx-font-size: 2em; -fx-text-fill: #ffffff;");

    }

    /* set text in result display field
     * If the length of text is greater than 11, adjust the size of text to fit the textfield */
    public void setResultText(String displayingText){
        if(displayingText.length() > 11){
            result.setStyle("-fx-border-color: #525050; -fx-border-width: 1px; -fx-background-color: #525050; -fx-font-size: " + (2.5-2.5/displayingText.length()*(displayingText.length()-10)) + "em; -fx-text-fill: #ffffff;");
        }
        else{
            result.setStyle("-fx-border-color: #525050; -fx-border-width: 1px; -fx-background-color: #525050; -fx-font-size: 2.5em; -fx-text-fill: #ffffff;");
        }
        result.setText(displayingText);
    }

    /* set text in operation display field
     * If the length of text is greater than 11, adjust the size of text to fit the textfield */
    public void setOperationText(String displayingText){
        if(displayingText.length() > 11){
            operation.setStyle("-fx-border-color: #525050; -fx-border-width: 1px; -fx-background-color: #525050; -fx-font-size: " + (2.5-2.5/displayingText.length()*(displayingText.length()-10)) + "em; -fx-text-fill: #ffffff;");
        }
        else{
            operation.setStyle("-fx-border-color: #525050; -fx-border-width: 1px; -fx-background-color: #525050; -fx-font-size: 2.5em; -fx-text-fill: #ffffff;");
        }
        operation.setText(displayingText);
    }



}
