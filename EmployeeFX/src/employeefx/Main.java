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
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Main extends Application{

    private Label mainLabel = new Label("Employee Records");
    private TextField idTxt = new TextField();
    private TextField nameTxt = new TextField();
    private TextField cityTxt = new TextField();
    private TextField positionTxt = new TextField();
    private Label lblID = new Label("ID: ");
    private Label lblName = new Label("Name: ");
    private Label lblCity = new Label("City: ");
    private Label lblPosition = new Label("Position: ");
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
    private Pane pane = new Pane();
    private Scene scene;

//    private GridPane getGrid(){
////        Pane pane = new GridPane();
////        pane.add(lblID, 0,0);
////        pane.add(lblName, 0,2);
////        pane.add(lblCity, 0,3);
////        pane.add(lblPosition, 0,4);
////        pane.add(idTxt, 1,0);
////        pane.add(nameTxt, 1,2);
////        pane.add(cityTxt, 1,3);
////        pane.add(positionTxt,1,4);
////        pane.add(pagingBox, 1,6,2,1);
////        pane.add(ActivityBox, 1, 5);
//                
////        return pane;
//    }
    
    public Pane getFullDesign() {
        Pane pane = new Pane();
        pane.getChildren().add(mainLabel);
        mainLabel.setLayoutX(60);
        mainLabel.setLayoutY(30);
        mainLabel.setFont(new Font(35));
        pane.getChildren().add(firstHBox);  
        firstHBox.setLayoutX(30);
        firstHBox.setLayoutY(80);
        pane.getChildren().add(secondHBox);
        secondHBox.setLayoutX(30);
        secondHBox.setLayoutY(120);
        pane.getChildren().add(ActivityBox);
        ActivityBox.setLayoutX(30);
        ActivityBox.setLayoutY(190);
        pane.getChildren().add(pagingBox);
        pagingBox.setLayoutX(30);
        pagingBox.setLayoutY(240);
        
        addBtn.setMinWidth(120);
        return pane;
//        pane.getChildren().addAll(mainLabel,firstHBox,secondHBox,,ActivityBox,pagingBox);
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
             scene = new Scene(pane, 400, 400);
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
                    Employee employee = new Employee((int)Integer.getInteger(idTxt.getText()),nameTxt.getText(),cityTxt.getText(),positionTxt.getText());
                    if(EmployeeFile.WriteRecord(Main.fileName, employee,true)){
                        ShowError.show("Success", "Record added to file","Information");
                        Main.employeeList = EmployeeFile.ReadRecords(Main.fileName);
                    }
                }
//                if(!checkInteger(idTxt.getText()) ) {
//                    throw new Exception("Please enter only integral numbers in ID field");
//                }
//                if(!checkString(nameTxt.getText())) {
//                    throw new Exception("Please enter only string value in name field");
//                }
//                if(!checkString(cityTxt.getText())) {
//                    throw new Exception("Please enter only string value in City field");
//                }
//                if(!checkString(positionTxt.getText())) {
//                    throw new Exception("Please enter only string value in position field");
//                }
                
            } catch (Exception e) {
                ShowError.show("Error", e.getMessage().toString());
            }
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
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
//                        ShowError.show("Success", "Record Updated to file","Information");
                        Main.employeeList = EmployeeFile.ReadRecords(Main.fileName);
                    }
                }
            } catch (Exception e) {
                ShowError.show("Error", e.getMessage().toString());
            }
//            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }  
    }
    
    public class DeleteRecords implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent t) {
            try {
                boolean checkDone = new CheckRecordToInsert().CheckRecordToInsert();
                if(checkDone){
                    Employee employee = new Employee((int)Integer.getInteger(idTxt.getText()),nameTxt.getText(),cityTxt.getText(),positionTxt.getText());
                    if(EmployeeFile.DeleteRecord(Main.fileName, employee)){
//                        ShowError.show("Success", "Record Updated to file","Information");
                        Main.employeeList = EmployeeFile.ReadRecords(Main.fileName);
                    }
                }
            } catch (Exception e) {
                ShowError.show("Error", e.getMessage().toString());
            }
//            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
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
//            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody    
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
//            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody    
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
//            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody    
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
//                    System.out.println("Previous " + currentIndex);
                    currentIndex = (currentIndex - 1) % Main.employeeList.size();
                    currentIndex = (currentIndex == -1) ? Main.employeeList.size() - 1 : currentIndex;
//                    System.out.println("Previous after " + currentIndex);
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
