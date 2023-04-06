/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package employeefx;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

/**
 *
 * @author tejan
 */
public class ShowError {
    private static Alert alert;
    public static void show(String title, String msg) {
        alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
            alert.setContentText(msg);
            alert.show();
    }
    public static boolean show(String title, String msg, String purpose) {
        boolean isDone = false;
        switch(purpose){
            case "Error":
                alert = new Alert(Alert.AlertType.ERROR);
                break;
            case "Confirmation":
                alert = new Alert(Alert.AlertType.CONFIRMATION);
                break;
            case "Information" :
                alert = new Alert(Alert.AlertType.INFORMATION);
                break;
            case "Warning":
                alert = new Alert(Alert.AlertType.WARNING);
                break;
            case "None":
                alert = new Alert(Alert.AlertType.NONE);
                break;

        }
        alert.setTitle(title);
        alert.setContentText(msg);
        if(purpose == "Confirmation") {
            alert.showAndWait();
            if (alert.getResult() == ButtonType.YES) {
                isDone = true;
            }
            else if (alert.getResult() == ButtonType.NO) {
                isDone = false;
            }
            else if (alert.getResult() == ButtonType.CLOSE) {
                isDone = false;
            }
        }
        else {
            alert.show();
        }
        return isDone;
    }
    
}
