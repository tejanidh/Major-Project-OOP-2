/**
 * Group of 2 people :-
 * 1. Dhruvkumar Tejani - 991677853
 * 2. Heli Patel - 991678820
 * Final Project
 * 9 April 2023
 */
package employeefx;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

public class EmployeeFile {
    public static ArrayList<Employee> ReadRecords(String fileName) {
        
        ArrayList<Employee> employeeList = new ArrayList<Employee>();
        FileReader fileReader;
        BufferedReader bufferedReader;
        try {
            
            File file = new File(fileName);
            if(!file.exists()){
                file.createNewFile();
                throw new FileNotFoundException("Given file name not found at path.");
            }
            fileReader = new FileReader(file);
            bufferedReader = new BufferedReader(fileReader);
            if(bufferedReader.ready()) {
                String Line = bufferedReader.readLine();

                while( Line != "" && Line != null) {
                    
                    String[] ConvertedLine = Line.split(",");
                    Employee employee = new Employee(Integer.parseInt(ConvertedLine[0].trim()),ConvertedLine[1].trim(),ConvertedLine[2].trim(),ConvertedLine[3].trim());
                    employeeList.add(employee);
                    Line = bufferedReader.readLine();
                }
            }
            else {
                ShowError.show("File Reading Error", "File is not available to read.");
            }
        }
        catch(FileNotFoundException e) {
            ShowError.show("Error", "File Not Found. Please try again by giving another file name\n" + e.getMessage().toString());
        }
        catch (Exception e) {
            ShowError.show("Error", "File Not Found. Please try again by giving another file name\n" + e.getMessage().toString());
        } finally {
        }
        System.out.println(employeeList);
        return employeeList;
    }
    public static boolean WriteRecords(String fileName,ArrayList<Employee> emp,boolean isAppend) {
        boolean temp = true;
        try {
           for(int i = 0; i < emp.size(); i++ ) {
                WriteRecord(fileName, emp.get(i), i == 0 ? isAppend : true);
            }           
        }
        catch (Exception e) {
            ShowError.show("Error",  e.getMessage().toString());
            temp = false;             
        }
        return temp;
    }
    
    public static boolean WriteRecord(String fileName,Employee emp,boolean isAppend) {
        boolean temp = true;
        try {
            
            File file = new File(fileName);
            if(!file.exists()){
                file.createNewFile();
                throw new FileNotFoundException("Given file name not found at path.");
            }
            FileWriter fw = new FileWriter(file,isAppend);
            BufferedWriter bWriter = new BufferedWriter(fw);
            
            bWriter.write(emp.getID() + ", " + emp.getName() + ", " + emp.getCity() + ", " + emp.getPosition());
            bWriter.flush();
            bWriter.newLine();
            bWriter.flush();
            bWriter.close();
            fw.close();
        }
        catch(FileNotFoundException e) {
            ShowError.show("Error", "File Not Found. Please try again by giving another file name\n" + e.getMessage().toString());
            temp = false;
        }
        catch (Exception e) {
            ShowError.show("Error",  e.getMessage().toString());
            temp = false;
                    
        }finally {
//            if(exAlert.getContentText() == null || exAlert.getContentText() == "") {
//                exAlert.show();
//            }
        }
        return temp;
    }
    
    public static boolean UpdateRecord(String fileName,Employee employee) {
        boolean isDone = false;
        ArrayList<Employee> employeeList = ReadRecords(fileName);

        try {
            for(int i = 0; i < employeeList.size(); i++) {
                if(employeeList.get(i).getID() == employee.getID()) {
                    employeeList.set(i, employee);
                    isDone = true;
                }
            }
            System.out.println(employeeList.size());
            if(!isDone){ 
                ShowError.show("Update Record Error", "Record Not Found.");
            }
            else {
               boolean isApproved = ShowError.show("Update Record Permission", "Do want to update this record?","Confirmation");
                System.out.println("isApproved " + isApproved);
                if(isApproved) {
                    System.out.println(employeeList);
                    if(WriteRecords(fileName, employeeList,false)){
                        ShowError.show("Update Success", "Record Updated Successfully.","Information");
                    }

                }
                else {
                    ShowError.show("Error", "Not approved for updation.");
                }
            }
        } catch (Exception e) {
            ShowError.show("Update Record Error", e.getMessage().toString());
        }
        
        
        return isDone;
    }
    
    public static boolean DeleteRecord(String fileName,Employee employee) {
        boolean isDone = false;
        ArrayList<Employee> employeeList = ReadRecords(fileName);

        try {
            for(int i = 0; i < employeeList.size(); i++) {
                if(employeeList.get(i).getID() == employee.getID()) {
                    employeeList.remove(i);
                    isDone = true;
                }
            }
        } catch (Exception e) {
            ShowError.show("Delete Record Error", e.getMessage().toString());
        }
        
        if(!isDone){ 
            ShowError.show("Delete Record Error", "Record Not Found.","Warning");
        }
        else {
            boolean isApproved = ShowError.show("Delete Record Permission", "Do you want to delete record?","Confirmation");
            if(isApproved) {
                WriteRecords(fileName, employeeList,false);
                ShowError.show("Delete Success", "Record Deleted Successfully.","Information");
            }
        }
        return isDone;
    }
    
}
