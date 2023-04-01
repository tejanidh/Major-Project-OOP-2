/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package employeefx;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.ArrayList;
import javafx.scene.control.Alert;

/**
 *
 * @author tejan
 */
public class EmployeeFile {
    public ArrayList<Employee> ReadData(String fileName) {
        
        ArrayList<Employee> employeeList = new ArrayList<Employee>();
        Alert exAlert = new Alert(Alert.AlertType.ERROR);
        try {
            File file = new File(fileName);
            if(!file.exists()){
                file.createNewFile();
                throw new FileNotFoundException("Given file name not found at path.");
            }
        }
        catch(FileNotFoundException e) {
            exAlert.setTitle("File Not Found. Please try again by giving another file name\n" + e.getMessage().toString());
            exAlert.setContentText(e.getMessage().toString());
        }
        catch (Exception e) {
            
        } finally {
        }
        return employeeList;
    }
    public boolean WriteFile(String fileName,Employee emp) {
        Alert exAlert = new Alert(Alert.AlertType.ERROR);
        boolean temp = true;
        try {
            File file = new File(fileName);
            if(!file.exists()){
                file.createNewFile();
                throw new FileNotFoundException("Given file name not found at path.");
            }
            FileWriter fw = new FileWriter(file);
            BufferedWriter bWriter = new BufferedWriter(fw);
            bWriter.write(emp.getID() + ", " + emp.getName() + ", " + emp.getCity() + ", " + emp.getPosition());
            bWriter.flush();
            bWriter.newLine();
            bWriter.flush();
            bWriter.close();
            fw.close();
        }
        catch(FileNotFoundException e) {
            exAlert.setTitle("File Not Found. Please try again by giving another file name\n" + e.getMessage().toString());
            exAlert.setContentText(e.getMessage().toString());
            temp = false;
        }
        catch (Exception e) {
            
            exAlert.setTitle("Got Exception");
            exAlert.setContentText(e.getMessage().toString());
            temp = false;
                    
        }finally {
            if(exAlert.getContentText() == null || exAlert.getContentText() == "") {
                exAlert.show();
            }
        }
        return temp;
    }
    
}
