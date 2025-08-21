
import java.io.Serializable;
import java.time.LocalDate;

public class Attendence implements Serializable {


    private int empId;
    private String name;
    private LocalDate date;
    private char status;
    
    public Attendence(int empId, String name, LocalDate date, char status) {
        this.empId = empId;
        this.name = name;
        this.date = date;
        this.status = status;
    }

    public int getEmpId() {
        return empId;
    }

    public String getName() {
        return name;
    }

    public LocalDate getDate() {
        return date;
    }

    public char getStatus() {
        return status;
    }



    @Override
    public String toString() {
        return "Attendence empId : " + empId + ", name=" + name + ", date=" + date + ", status=" + status;
    }

    


    

}
