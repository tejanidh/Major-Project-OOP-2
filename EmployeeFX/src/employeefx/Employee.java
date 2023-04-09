/**
 * Group of 2 people :-
 * 1. Dhruvkumar Tejani - 991677853
 * 2. Heli Patel - 991678820
 * Final Project
 * 9 April 2023
 */
package employeefx;

public class Employee {
    private int ID;
    private String Name;
    private String City;
    private String Position;
    
    public Employee() {
        ID = 0;
        Name = "";
        City = "";
        Position  = "";
    }

    public Employee(int ID, String Name, String City, String Position) {
        this.setID(ID);
        this.setName(Name);
        this.setCity(City);
        this.setPosition(Position);
    }
     
    public int getID() {
        return ID;
    }

    public String getName() {
        return Name;
    }

    public String getCity() {
        return City;
    }

    public String getPosition() {
        return Position;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public void setCity(String City) {
        this.City = City;
    }

    public void setPosition(String Position) {
        this.Position = Position;
    }
    
    
    
}
