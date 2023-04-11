package employeefx.Search;

import employeefx.Employee;
import employeefx.ShowError;
import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Border;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class SearchOption extends Stage{
    
    private Label filter = new Label("Search Here");
    private ComboBox comboBox = new ComboBox();
    private Label lblSearch = new Label("Enter Id:");
    private Label lblSearchBy = new Label("Search By:");
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
        Scene scene = new Scene(pane,1150,900);
        try {
            
        filter.setLayoutX(130);
        filter.setLayoutY(10);
        filter.setStyle("-fx-color: brown; -fx-font-size: 40px; -fx-font-weight: bolder; ");
        comboBox.getItems().add("Id");
        comboBox.setValue("Id");
        comboBox.getItems().add("Name");
        comboBox.getItems().add("City");
        comboBox.getItems().add("Position");
        comboBox.setLayoutX(10);
        comboBox.setLayoutY(100);
        
        txtSearch.setLayoutX(160);
        txtSearch.setLayoutY(100);
        lblSearch.setLayoutX(160);
        lblSearch.setLayoutY(80);
        lblSearchBy.setLayoutX(10);
        lblSearchBy.setLayoutY(80);
        searchBtn.setLayoutX(460);
        searchBtn.setLayoutY(95);
        searchBtn.setMinWidth(120);
        searchBtn.setFont(Font.font(20));
        searchBtn.setStyle("-fx-border-color: green; -fx-background-color: lightgreen;  -fx-font-color: green; ");
        searchBtn.setTextFill(Color.GREEN);
        txtSearch.setStyle("-fx-background-color: #f8f8f7; -fx-border-color: gray; -fx-color: gray; -fx-border-radius: 15;");
        comboBox.setStyle(" -fx-width: 200px; -fx-font-size: 20px;");
        
//        comboBox.setFont(Font.font("Serif", FontWeight.SEMI_BOLD, FontPosture.REGULAR, 16));
        

        searchedDataGridPane.setLayoutX(60);
        searchedDataGridPane.setLayoutY(190);
        searchedDataGridPane.setStyle(" -fx-font-size: 15px; -fx-border-color: red;");
        searchedDataGridPane.setGridLinesVisible(true);
        lblSearch.setStyle("-fx-font-size: 14;");
        lblSearchBy.setStyle("-fx-font-size: 14;");
        
        
        txtSearch.setMinHeight(25);
        txtSearch.setMinWidth(120); 
        txtSearch.setStyle("-fx-background-color: #f8f8f7; -fx-border-color: gray; -fx-color: gray; -fx-border-radius: 15; -fx-border-insets: 5;    -fx-border-color: black; ");
        txtSearch.setFont(new Font(20));
        txtSearch.setPrefColumnCount(12);
        txtSearch.setPromptText("Enter Id");
        txtSearch.setFocusTraversable(true);
                      
        pane.getChildren().addAll(filter,lblSearchBy,comboBox,lblSearch,txtSearch,searchBtn, searchedDataGridPane);
        scene = new Scene(pane);
        System.out.println(" Lini " + scene.getStylesheets());
        scene.getStylesheets().add("./src/employeefx/style/Main.css");
        System.out.println("Link " + scene.getStylesheets());
        searchedDataGridPane.getStyleClass().add("grid");
         searchedDataGridPane.setPadding(new Insets(10, 10, 10, 10));
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
                    txtSearch.setPromptText("Enter Id");
                    break;
                case "Name":
                    lblSearch.setText("Enter Name : ");
                    txtSearch.setPromptText("Enter Name");
                    break;
                case "City":
                    lblSearch.setText("Enter City : ");
                    txtSearch.setPromptText("Enter City");
                    break;
                case "Position":
                    lblSearch.setText("Enter Position : ");
                    txtSearch.setPromptText("Enter Position");
                    break;
            }
        }       
    }
    
    public class SearchData implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent t) {
            try {
                searchedDataGridPane.getChildren().clear();
//            searchedDataGridPane.setHgap(25);
            searchedDataGridPane.setAlignment(Pos.CENTER);
//            searchedDataGridPane.setVgap(15);
            if((String)comboBox.getValue() != "" && (String)comboBox.getValue() != null  ) {
                
                String field = (String)comboBox.getValue();
                if(field == "Id" && (txtSearch.getText() == "" || txtSearch.getText() == null)) {
                    ShowError.show("Error", "Please enter any data");
                }
                else {
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
                    searchedDataGridPane.getChildren().get(0).setStyle("-fx-border-size:2px; -fx-border-color: black; -fx-padding: 15px; -fx-min-width:180px; -fx-font-weight: bolder; -fx-font-size: 25px; -fx-background-color: lightgray; -fx-align-items:center;");
                    searchedDataGridPane.addColumn(1, lblName);
                    searchedDataGridPane.getChildren().get(1).setStyle("-fx-border-size:2px; -fx-border-color: black; -fx-padding: 15px; -fx-min-width:180px; -fx-font-weight: bolder; -fx-font-size: 25px; -fx-background-color: lightgray;");
                    searchedDataGridPane.addColumn(2, lblCity);
                    searchedDataGridPane.getChildren().get(2).setStyle("-fx-border-size:2px; -fx-border-color: black; -fx-padding: 15px; -fx-min-width:180px; -fx-font-weight: bolder; -fx-font-size: 25px; -fx-background-color: lightgray;");
                    searchedDataGridPane.addColumn(3, lblPosition);
                    searchedDataGridPane.getChildren().get(3).setStyle("-fx-border-size:2px; -fx-border-color: black; -fx-padding: 15px; -fx-min-width:180px; -fx-font-weight: bolder; -fx-font-size: 25px; -fx-background-color: lightgray;");

                    for(int i = 0; i < SearchedList.size(); i++) {
                        searchedDataGridPane.add(new Label(String.valueOf(SearchedList.get(i).getID())),0, i + 1); 
                        searchedDataGridPane.getChildren().get(((i+ 1) * 4) + 0).setStyle("-fx-border-size:2px; -fx-border-color: black; -fx-padding: 15px; -fx-min-width:180px;");
                        searchedDataGridPane.add(new Label(String.valueOf(SearchedList.get(i).getName())), 1, i + 1);
                        searchedDataGridPane.getChildren().get(((i+ 1) * 4) + 1).setStyle("-fx-border-size:2px; -fx-border-color: black; -fx-padding: 15px; -fx-min-width:180px;");
                        searchedDataGridPane.add(new Label(String.valueOf(SearchedList.get(i).getCity())), 2, i + 1);
                        searchedDataGridPane.getChildren().get(((i+ 1) * 4) + 2).setStyle("-fx-border-size:2px; -fx-border-color: black; -fx-padding: 15px; -fx-min-width:180px;");
                        searchedDataGridPane.add(new Label(String.valueOf(SearchedList.get(i).getPosition())), 3, i + 1);   
                        searchedDataGridPane.getChildren().get(((i+ 1) * 4) + 3 ).setStyle("-fx-border-size:2px; -fx-border-color: black; -fx-padding: 15px; -fx-min-width:180px;");
                    }
                }
                }
            }
            else {
                ShowError.show("Error", "Please select any of search option");
            }
            } catch (Exception e) {
                ShowError.show("Error", "There is an error. PLease enter any particular data.");
            }
            
        }        
    }  
}
