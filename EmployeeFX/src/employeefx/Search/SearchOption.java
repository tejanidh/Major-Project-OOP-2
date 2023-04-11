package employeefx.Search;

import employeefx.Employee;
import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class SearchOption extends Stage{
    
    private Label filter = new Label("Search Here");
    private ComboBox comboBox = new ComboBox();
    private Label lblSearch = new Label("");
    private TextField txtSearch = new TextField();
    private Button searchBtn = new Button("Search");
    private Pane pane = new Pane();
    private static ArrayList<Employee> employeeList = new ArrayList<Employee>();
    private final static String fileName = ".\\src\\employeefx\\Employee.dat";
    private GridPane searchedDataGridPane = new GridPane();
    private Label lblID = new Label("ID ");
    private Label lblName = new Label("Name ");
    private Label lblCity = new Label("City");
    private Label lblPosition = new Label("Position");
           private Scene searchScene;

    public SearchOption() {
        searchScene = getSearchBody();
        setTitle("Search Here");
        setScene(searchScene);
        comboBox.setOnAction(new ChangeLabel());
        searchBtn.setOnAction(new SearchData());
    }
    
    public Scene getSearchBody() {
        Scene scene = new Scene(pane,600,600);
        try {
            
        filter.setLayoutX(130);
        filter.setLayoutY(10);
        
        comboBox.getItems().add("Id");
        comboBox.getItems().add("Name");
        comboBox.getItems().add("City");
        comboBox.getItems().add("Position");
        comboBox.setLayoutX(10);
        comboBox.setLayoutY(60);
        
        txtSearch.setLayoutX(160);
        txtSearch.setLayoutY(60);
        lblSearch.setLayoutX(160);
        lblSearch.setLayoutY(45);
        searchBtn.setLayoutX(360);
        searchBtn.setLayoutY(60);
        
        searchedDataGridPane.setLayoutX(60);
        searchedDataGridPane.setLayoutY(90);
                      
        pane.getChildren().addAll(filter,comboBox,lblSearch,txtSearch,searchBtn, searchedDataGridPane);
        scene = new Scene(pane);
        System.out.println(" Lini " + scene.getStylesheets());
        scene.getStylesheets().add("./src/employeefx/style/Main.css");
        System.out.println("Link " + scene.getStylesheets());
        searchedDataGridPane.getStyleClass().add("grid");
        filter.getStyleClass().add("font-family");
      
        } catch (Exception e) {
        }
        return scene;
    }
    
    public class ChangeLabel implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent t) {
            
            String field  = (String)comboBox.getValue();
            switch(field) { 
                case "Id":
                    lblSearch.setText("Enter Id : ");
                    break;
                case "Name":
                    lblSearch.setText("Enter Name : ");
                    break;
                case "City":
                    lblSearch.setText("Enter City : ");
                    break;
                case "Position":
                    lblSearch.setText("Enter Position : ");
                    break;
            }
        }       
    }
    
    public class SearchData implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent t) {
            searchedDataGridPane.getChildren().clear();
            String field = (String)comboBox.getValue();
            ArrayList<Employee> SearchedList = new ArrayList<Employee>();
            SearchOption.employeeList = employeefx.EmployeeFile.ReadRecords(SearchOption.fileName);
            for(int i = 0; i < SearchOption.employeeList.size(); i++){
               switch(field) { 
                case "Id":
                    if(SearchOption.employeeList.get(i).getID() == (int)Integer.parseInt(txtSearch.getText())) {
                        SearchedList.add(SearchOption.employeeList.get(i));
                    }
                    break;
                case "Name":
                    if(SearchOption.employeeList.get(i).getName().contains(txtSearch.getText())) {
                        SearchedList.add(SearchOption.employeeList.get(i));
                    }
                    break;
                case "City":
                    if(SearchOption.employeeList.get(i).getCity().contains(txtSearch.getText())) {
                        SearchedList.add(SearchOption.employeeList.get(i));
                    }
                    break;
                case "Position":
                    if(SearchOption.employeeList.get(i).getPosition().contains(txtSearch.getText())) {
                        SearchedList.add(SearchOption.employeeList.get(i));
                    }
                    break;
                }
            }
                        
            if(SearchedList.size() > 0) {
                searchedDataGridPane.addColumn(0, lblID); 
                searchedDataGridPane.addColumn(1, lblName);
                searchedDataGridPane.addColumn(2, lblCity);
                searchedDataGridPane.addColumn(3, lblPosition);
                
                for(int i = 0; i < SearchedList.size(); i++) {
                    searchedDataGridPane.add(new Label(String.valueOf(SearchedList.get(i).getID())),0, i + 1);                
                    searchedDataGridPane.add(new Label(String.valueOf(SearchedList.get(i).getName())), 1, i + 1);
                    searchedDataGridPane.add(new Label(String.valueOf(SearchedList.get(i).getCity())), 2, i + 1);
                    searchedDataGridPane.add(new Label(String.valueOf(SearchedList.get(i).getPosition())), 3, i + 1);   
                }
            }
        }        
    }  
}
