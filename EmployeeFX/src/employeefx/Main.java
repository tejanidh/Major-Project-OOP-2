/**
 * Group of 2 people :-
 * 1. Dhruvkumar Tejani - 991677853
 * 2. Heli Patel - 991678820
 * Final Project
 * 9 April 2023
 */

package employeefx;

import employeefx.Search.SearchOption;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Border;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class Main extends Application{

    private Label mainLabel = new Label("Employee Records");
    private TextField idTxt = new TextField();
    private TextField nameTxt = new TextField();
    private TextField cityTxt = new TextField();
    private TextField positionTxt = new TextField();
    private Label lblID = new Label("Employee ID: ");
    private Label lblName = new Label("Employee Name: ");
    private Label lblCity = new Label("Employee City: ");
    private Label lblPosition = new Label("Employee Position: ");
    private Button firstBtn = new Button("FIRST");
    private Button nextBtn = new Button("NEXT");
    private Button previousBtn = new Button("PREVIOUS");
    private Button lastBtn = new Button("LAST");
    private Button addBtn = new Button("ADD");
    private Button updateBtn = new Button("UPDATE");
    private Button searchBtn = new Button("SEARCH");
    private Button deleteBtn = new Button("DELETE");
    private Label idErrLbl = new Label("Id");
    private Label nameErrLbl = new Label("Name");
    private Label cityErrLbl = new Label("City");
    private Label positionErrLbl = new Label("Position");
    private VBox idVBox = new VBox(lblID,idTxt); 
    private VBox nameVBox = new VBox(lblName,nameTxt);
    private VBox cityVBox = new VBox(lblCity,cityTxt);
    private VBox positionVBox = new VBox(lblPosition,positionTxt);
   
    private HBox firstHBox =  new HBox(idVBox,nameVBox); 
    private HBox secondHBox =  new HBox(cityVBox,positionVBox);
            
    private static ArrayList<Employee> employeeList = new ArrayList<Employee>();
    private final static String fileName = ".\\src\\employeefx\\Employee.dat";
    private static int currentIndex = 0;
    
    private HBox pagingBox = new HBox(firstBtn,previousBtn,nextBtn,lastBtn);
    private HBox ActivityBox = new HBox(addBtn,updateBtn,deleteBtn,searchBtn);
    
    private VBox finalInputVBox = new VBox(mainLabel,firstHBox,secondHBox,ActivityBox,pagingBox);
    private Pane pane = new Pane();
    private Scene scene;
    
    public Pane getFullDesign() {
        Pane pane = new Pane();
        pane.getChildren().add(mainLabel);
        mainLabel.setLayoutX(150);
        mainLabel.setTextFill(Color.CHOCOLATE);
        mainLabel.setLayoutY(30);
        mainLabel.setFont(Font.font("Serif", FontWeight.BOLD, FontPosture.REGULAR, 35));
//        mainLabel.setMinWidth(300);
        mainLabel.setAlignment(Pos.CENTER);
//        pane.getChildren().add(firstHBox); 
        finalInputVBox.setLayoutY(80);
        finalInputVBox.setLayoutX(30);
        finalInputVBox.setSpacing(20);
        pane.getChildren().add(finalInputVBox);
//        firstHBox.setLayoutY(80);
//        pane.getChildren().add(secondHBox);
//        secondHBox.setLayoutX(30);
//        secondHBox.setLayoutY(120);
//        pane.getChildren().add(ActivityBox);
//        ActivityBox.setLayoutX(30);
//        ActivityBox.setLayoutY(190);
//        pane.getChildren().add(pagingBox);
//        pagingBox.setLayoutX(30);
//        pagingBox.setLayoutY(300);
        
        addBtn.setMinWidth(120);
        updateBtn.setMinWidth(120);
        searchBtn.setMinWidth(120);
        deleteBtn.setMinWidth(120);
        firstBtn.setMinWidth(120);
        lastBtn.setMinWidth(120);
        nextBtn.setMinWidth(120);
        previousBtn.setMinWidth(120);
        firstHBox.setSpacing(25);
        secondHBox.setSpacing(25);
        pagingBox.setSpacing(20);
        ActivityBox.setSpacing(20);
        
        addBtn.setFont(Font.font("Serif", FontWeight.SEMI_BOLD, FontPosture.REGULAR, 16));
        
        updateBtn.setFont(Font.font("Serif", FontWeight.SEMI_BOLD, FontPosture.REGULAR, 16));
        searchBtn.setFont(Font.font("Serif", FontWeight.SEMI_BOLD, FontPosture.REGULAR, 16));
        deleteBtn.setFont(Font.font("Serif", FontWeight.SEMI_BOLD, FontPosture.REGULAR, 16));
        firstBtn.setFont(Font.font("Serif", FontWeight.SEMI_BOLD, FontPosture.REGULAR, 16));
        lastBtn.setFont(Font.font("Serif", FontWeight.SEMI_BOLD, FontPosture.REGULAR, 16));
        nextBtn.setFont(Font.font("Serif", FontWeight.SEMI_BOLD, FontPosture.REGULAR, 16));
        previousBtn.setFont(Font.font("Serif", FontWeight.SEMI_BOLD, FontPosture.REGULAR, 16));
        
        firstBtn.setStyle("-fx-border-color: gray; -fx-background-color: lightgray; -fx-font-color: gray;");
        firstBtn.setTextFill(Color.GRAY);
        lastBtn.setStyle("-fx-border-color: gray; -fx-background-color: lightgray; ");
        lastBtn.setTextFill(Color.GRAY);
        nextBtn.setStyle("-fx-border-color: blue; -fx-background-color: skyblue; ");
        nextBtn.setTextFill(Color.BLUE);
        previousBtn.setStyle("-fx-border-color: blue; -fx-background-color: skyblue; ");
        previousBtn.setTextFill(Color.BLUE);
        addBtn.setStyle("-fx-border-color: green; -fx-background-color: lightgreen;  -fx-font-color: green;");
        addBtn.setTextFill(Color.GREEN);
        deleteBtn.setStyle("-fx-border-color: red; -fx-background-color: #f5a19b; ");
        deleteBtn.setTextFill(Color.RED);
        updateBtn.setStyle("-fx-border-color: blue; -fx-background-color: lightblue; ");
        updateBtn.setTextFill(Color.BLUE);
        
        searchBtn.setStyle("-fx-border-color: #b7ee33; -fx-background-color: #f3f8e7 ; ");
        searchBtn.setTextFill(Color.DARKGREEN);
        
        
        lblID.setStyle("-fx-font-size: 14;");
        lblName.setStyle("-fx-font-size: 14;");
        lblCity.setStyle("-fx-font-size: 14;");
        lblPosition.setStyle("-fx-font-size: 14;");
        
        idTxt.setMinHeight(25);
        idTxt.setMinWidth(120); 
        idTxt.setStyle("-fx-background-color: #f8f8f7; -fx-border-color: gray; -fx-color: gray; -fx-border-radius: 15;");
        idTxt.setFont(new Font(20));
        idTxt.setPrefColumnCount(12);
        idTxt.setPromptText("Enter Id");
        idTxt.setFocusTraversable(true);
        
        nameTxt.setMinHeight(25);
        nameTxt.setPrefColumnCount(12);
        nameTxt.setMinWidth(120); 
        nameTxt.setFont(new Font(20));
        nameTxt.setStyle("-fx-background-color: #f8f8f7; -fx-border-color: gray; -fx-color: gray; -fx-border-radius: 15;");
        nameTxt.setPromptText("Enter Name");
        
        cityTxt.setMinHeight(25);
        cityTxt.setMinWidth(120); 
        cityTxt.setFont(new Font(20));
        cityTxt.setPrefColumnCount(12);
        cityTxt.setStyle("-fx-background-color: #f8f8f7; -fx-border-color: gray; -fx-color: gray; -fx-border-radius: 15;");
        cityTxt.setPromptText("Enter City name");
        
        positionTxt.setMinHeight(25);
        positionTxt.setMinWidth(120); 
        positionTxt.setFont(new Font(20));
        positionTxt.setStyle("-fx-background-color: #f8f8f7; -fx-border-color: gray; -fx-color: gray; -fx-border-radius: 15;");
        positionTxt.setPrefColumnCount(12);
        positionTxt.setPromptText("Enter Position");
        return pane;      

    }
    
    @Override
    public void start(Stage stage)  {
        try {
            
            Main.employeeList = EmployeeFile.ReadRecords(Main.fileName);
            idTxt.setOnKeyTyped(eh -> {
                try {
                    new checkIsNumberTyped(eh.getCharacter());
                } catch (Exception e) {
                    ShowError.show("Input Error", "Please enter only numeric value without decimal.","Error");
                }
            });
            nameTxt.setOnKeyTyped(eh -> {
                try {
                    new checkIsNameStringTyped(eh.getCharacter());
                } catch (Exception e) {
                    ShowError.show("Input Error", "Please enter only string value without decimal.","Error");
                }
            });
            cityTxt.setOnKeyTyped(eh -> {
                try {
                    new checkIsCityStringTyped(eh.getCharacter());
                } catch (Exception e) {
                    ShowError.show("Input Error", "Please enter only String value without decimal.","Error");
                }
            });
            positionTxt.setOnKeyTyped(eh -> {
                try {
                    new checkIsPOsitionStringTyped(eh.getCharacter());
                } catch (Exception e) {
                    ShowError.show("Input Error", "Please enter only string value without decimal.","Error");
                }
            });

            addBtn.setOnAction(new AddRecords());  
            updateBtn.setOnAction(new UpdateRecords());
            deleteBtn.setOnAction(new DeleteRecords());
            nextBtn.setOnAction(new GetNextRecord());
            lastBtn.setOnAction(new GetLastRecord());
            firstBtn.setOnAction(new GetFirstRecord());
            previousBtn.setOnAction(new GetPreviousRecord());
            searchBtn.setOnAction(eh -> {
                SearchOption searchOption = new SearchOption();
                searchOption.getScene().getStylesheets().add("style\\Main.css");
                System.out.println(searchOption.getScene().getStylesheets());
                searchOption.show();
            });
            pane = getFullDesign();
             scene = new Scene(pane, 600, 400);
            stage.setScene(scene);  
            stage.setTitle("Employee Details");
            stage.show();
        } catch (Exception e) {
        }
        
          
    }
    
    public static void main(String[] args) {
        Application.launch(args);
    }
    public final static boolean checkInteger(String str) {
        return str.matches("[0-9]{1,}");
    }
    public final static boolean checkString(String str) {  
        System.out.println(str);
        return str.matches("[a-zA-Z_0-9\\s]{1,}");
    }
    public class CheckRecordToInsert {
        public boolean CheckRecordToInsert() {
            boolean var = true;
            try {
                if(!checkInteger(idTxt.getText()) ) {
                    throw new Exception("Please enter only integral numbers in ID field");
                }
                if(!checkString(nameTxt.getText())) {
                    throw new Exception("Please enter only string value in name field");
                }
                if(!checkString(cityTxt.getText())) {
                    throw new Exception("Please enter only string value in City field");
                }
                if(!checkString(positionTxt.getText())) {
                    throw new Exception("Please enter only string value in position field");
                }
            }
            catch(Exception e) {
                
                ShowError.show("Error", e.getMessage().toString(), "Error");
                var = false;
            }
            return var;
        }
            
    }
    
    public class AddRecords implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent t) {
            try {
                boolean checkDone = new CheckRecordToInsert().CheckRecordToInsert();
                if(checkDone){
                    Main.employeeList = EmployeeFile.ReadRecords(Main.fileName);
                    boolean checkWriteable = true;
                    for(int i = 0; i < Main.employeeList.size(); i++) {
                        if(Main.employeeList.get(i).getID() == (int)Integer.parseInt(idTxt.getText())) {
                            checkWriteable = false;
                        }
                    }
                    if(checkWriteable) {
                        Employee employee = new Employee((int)Integer.parseInt(idTxt.getText()),nameTxt.getText(),cityTxt.getText(),positionTxt.getText());
                        if(EmployeeFile.WriteRecord(Main.fileName, employee,true)){
                            ShowError.show("Success", "Record added to file","Information");
                            Main.employeeList = EmployeeFile.ReadRecords(Main.fileName);
                        }
                    }
                    else {
                        ShowError.show("Error", "Please change Id. we found already one.");
                    }
                }                
            } catch (Exception e) {
                ShowError.show("Error", e.getMessage().toString());
            }
//            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }  
    }
    
    public class UpdateRecords implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent t) {
            try {
                boolean checkDone = new CheckRecordToInsert().CheckRecordToInsert();
                if(checkDone){
                    Employee employee = new Employee((int)Integer.parseInt(idTxt.getText()),nameTxt.getText(),cityTxt.getText(),positionTxt.getText());
                    if(EmployeeFile.UpdateRecord(Main.fileName, employee)){
                        Main.employeeList = EmployeeFile.ReadRecords(Main.fileName);
                    }
                }
            } catch (Exception e) {
                ShowError.show("Error", e.getMessage().toString());
            }
        }  
    }
    
    public class DeleteRecords implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent t) {
            try {
                boolean checkDone = new CheckRecordToInsert().CheckRecordToInsert();
                if(checkDone){
                    Employee employee = new Employee((int)Integer.parseInt(idTxt.getText()),nameTxt.getText(),cityTxt.getText(),positionTxt.getText());
                    if(EmployeeFile.DeleteRecord(Main.fileName, employee)){
                        Main.employeeList = EmployeeFile.ReadRecords(Main.fileName);
                    }
                }
            } catch (Exception e) {
                ShowError.show("Error", e.getMessage().toString());
            }
        }  
    }
    
    public class GetFirstRecord implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent t) {
            try {
                Main.employeeList = EmployeeFile.ReadRecords(Main.fileName);
                if(Main.employeeList.isEmpty()) {
                    ShowError.show("Warning", "There is no record Found","Warning");
                }
                else {
                    new ShowRecord().showRecord(Main.employeeList.get(0));
                    currentIndex = 0;
                }
            } catch (Exception e) {
                ShowError.show("Error", e.getMessage().toString());
            } 
        } 
    }
    
    public class GetLastRecord implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent t) {
            try {
                Main.employeeList = EmployeeFile.ReadRecords(Main.fileName);
                if(Main.employeeList.isEmpty()) {
                    ShowError.show("Warning", "There is no record Found","Warning");
                }
                else {
                    currentIndex = Main.employeeList.size() - 1;
                    new ShowRecord().showRecord(Main.employeeList.get(currentIndex));
                }
            } catch (Exception e) {
                ShowError.show("Error", e.getMessage().toString());
            }
        }      
    }
    
    public class GetNextRecord implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent t) {
            try {
                Main.employeeList = EmployeeFile.ReadRecords(Main.fileName);
                if(Main.employeeList.isEmpty()) {
                    ShowError.show("Warning", "There is no record Found","Warning");
                }
                else {
                    currentIndex = (currentIndex + 1) % Main.employeeList.size();
                    new ShowRecord().showRecord(Main.employeeList.get(currentIndex));
                }
            } catch (Exception e) {
                ShowError.show("Error", e.getMessage().toString());
            }
        }        
    }
    
    public class GetPreviousRecord implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent t) {
            try {
                Main.employeeList = EmployeeFile.ReadRecords(Main.fileName);
                if(Main.employeeList.isEmpty()) {
                    ShowError.show("Warning", "There is no record Found","Warning");
                }
                else {
                    currentIndex = (currentIndex - 1) % Main.employeeList.size();
                    currentIndex = (currentIndex == -1) ? Main.employeeList.size() - 1 : currentIndex;
                    new ShowRecord().showRecord(Main.employeeList.get(currentIndex));
                }
            } catch (Exception e) {
                ShowError.show("Error", e.getMessage().toString());
            }
//            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody    
        }
        
    }
    
    public class ShowRecord {
        public void showRecord(Employee employee){
            idTxt.setText(String.valueOf(employee.getID()));
            nameTxt.setText(String.valueOf(employee.getName()));
            cityTxt.setText(String.valueOf(employee.getCity()));
            positionTxt.setText(String.valueOf(employee.getPosition()));
        }
    }

    public class checkIsNumberTyped implements EventHandler<KeyEvent> {
        private String currentStr; 
        
        public checkIsNumberTyped() {
        }
        public checkIsNumberTyped(String c) {
            currentStr = c;
        }
        
        @Override
        public void handle(KeyEvent event) {
            try {
                if (!currentStr.matches("[0-9]")){
                    event.consume();
                    idTxt.backward();
                    idTxt.deleteNextChar();
                }
//                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            } catch (Exception e) {
                ShowError.show("Error", e.getMessage().toString());
            }
            
        }
        
    }
    
    public class checkIsNameStringTyped implements EventHandler<KeyEvent> {
        private String currentStr; 
        public checkIsNameStringTyped() {
        }
        public checkIsNameStringTyped(String c) {
            currentStr = c;
        }
        
        @Override
        public void handle(KeyEvent event) {
            try {
                if (!currentStr.matches("\\w")){
                    event.consume();
                    nameTxt.backward();
                    nameTxt.deleteNextChar();
                }
                
//                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody

            } catch (Exception e) {
                ShowError.show("Error", e.getMessage().toString());
            }
        }
        
    }
    
    public class checkIsCityStringTyped implements EventHandler<KeyEvent> {
        private String currentStr; 
        public checkIsCityStringTyped() {
        }
        public checkIsCityStringTyped(String c) {
            currentStr = c;
        }
        
        @Override
        public void handle(KeyEvent event) {
            try {
                if (!currentStr.matches("\\w")){
                    event.consume();
                    cityTxt.backward();
                    cityTxt.deleteNextChar();
                }
                
//                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody

            } catch (Exception e) {
                ShowError.show("Error", e.getMessage().toString());
            }
        }
        
    }
    
    public class checkIsPOsitionStringTyped implements EventHandler<KeyEvent> {
        private String currentStr; 
        public checkIsPOsitionStringTyped() {
        }
        public checkIsPOsitionStringTyped(String c) {
            currentStr = c;
        }
        
        @Override
        public void handle(KeyEvent event) {
            try {
                if (!currentStr.matches("\\w")){
                    event.consume();
                    positionTxt.backward();
                    positionTxt.deleteNextChar();
                }
                
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody

            } catch (Exception e) {
                ShowError.show("Error", e.getMessage().toString());
            }
        }
        
    }
}
