/**
 * Group of 2 people :-
 * 1. Dhruvkumar Tejani - 991677853
 * 2. Heli Patel - 991678820
 * Final Project
 * 9 April 2023
 */

package employeefx;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Main extends Application{

    private TextField ID = new TextField();
    private TextField name = new TextField();
    private TextField city = new TextField();
    private TextField position = new TextField();
    private Label lblID = new Label("ID: ");
    private Label lblName = new Label("Name: ");
    private Label lblCity = new Label("City: ");
    private Label lblPosition = new Label("Position: ");
    private Button btnFirst = new Button("FIRST");
    private Button btnNext = new Button("NEXT");
    private Button btnPrevious = new Button("PREVIOUS");
    private Button btnLast = new Button("LAST");
    private Button btnAdd = new Button("ADD");
    private Button btnUpdate = new Button("UPDATE");
    private Button btnSearch = new Button("SEARCH");
    private Button btnDelete = new Button("DELETE");

    private GridPane getGrid(){
        GridPane Pane = new GridPane();
        Pane.add(lblID, 0,0);
        Pane.add(lblName, 0,2);
        Pane.add(lblCity, 0,3);
        Pane.add(lblPosition, 0,4);
        Pane.add(ID, 1,0);
        Pane.add(name, 1,2);
        Pane.add(city, 1,3);
        Pane.add(position,1,4);
        Pane.add(btnFirst, 0,6);
        Pane.add(btnNext, 1,6);
        Pane.add(btnPrevious, 2,6);
        Pane.add(btnLast, 3,6);
        Pane.add(btnAdd, 0, 5);
        Pane.add(btnUpdate, 1, 5);
        Pane.add(btnSearch, 2, 5);
        Pane.add(btnDelete, 3, 5);
                
        return Pane;
    }
    
    @Override
    public void start(Stage stage)  {
        stage.show();
        Scene scene = new Scene(getGrid(), 400, 300);
        stage.setScene(scene);    
    }
    
    public static void main(String[] args) {
        Application.launch(args);
    }
}
