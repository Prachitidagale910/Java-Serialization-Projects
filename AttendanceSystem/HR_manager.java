
import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.*;

public class HR_manager {

    private List<Attendence> attendences = new ArrayList<>();
    private Scanner sc = new Scanner(System.in);

    // Add new attendance
    public void addAttendence() {
        try {
            System.out.print("Enter Emp id: ");
            int id = sc.nextInt();
            sc.nextLine();

            System.out.print("Enter Emp name: ");
            String name = sc.nextLine();

            System.out.print("Enter date (yyyy-MM-dd): ");
            String dateStr = sc.nextLine();
            LocalDate date = LocalDate.parse(dateStr);

            System.out.print("Enter status (P/A): ");
            char status = sc.next().charAt(0);

            attendences.add(new Attendence(id, name, date, status));
            System.out.println("Attendance added successfully!");
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date format. Please use yyyy-MM-dd.");
        } catch (InputMismatchException e) {
            System.out.println("Invalid input type.");
            sc.nextLine(); // clear the buffer
        }
    }

    // View all attendance
    public void viewAttendence() {
        if (attendences.isEmpty()) {
            System.out.println("No attendance records found!");
            return;
        }
        for (Attendence at : attendences) {
            System.out.println(at);
        }
    }

    // Get employee details by ID
    public void getEmpDetails(int id) {
        Attendence at = searchById(id);
        if (at != null) {
            System.out.println(at);
        } else {
            System.out.println("Employee record not found!");
        }
    }

    // Search employee by ID
    public Attendence searchById(int id) {
        for (Attendence at : attendences) {
            if (at.getEmpId() == id) {
                return at;
            }
        }
        return null;
    }

    // Search employee by date
    public Attendence searchByDate(LocalDate d) {
        for (Attendence at : attendences) {
            if (at.getDate().equals(d)) { // use equals() instead of ==
                return at;
            }
        }
        return null;
    }

    // Save attendance to file
    public void saveEmpData() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("emp_att.ser"))) {
            oos.writeObject(attendences);
            System.out.println("Data saved successfully!");
        } catch (IOException i) {
            System.out.println("Error saving data: " + i.getMessage());
        }
    }

    // Read attendance from file
    @SuppressWarnings("unchecked")
    public void readEmpData() {
        File file = new File("emp_att.ser");
        if (!file.exists()) {
            System.out.println("No previous data found. Starting fresh!");
            return;
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            attendences = (List<Attendence>) ois.readObject();
            System.out.println("Data loaded successfully!");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error reading data: " + e.getMessage());
        }
    }

    // Menu for operations
    public void start() {
        System.out.println("\n--- Employee Attendance Menu ---");
        System.out.println("1. Add Attendance");
        System.out.println("2. View Attendance");
        System.out.println("3. Search Employee by ID");
        System.out.println("4. Save Attendance to File");
        System.out.println("5. Exit");
        System.out.print("Enter your choice: ");

        int choice = sc.nextInt();
        sc.nextLine(); // consume newline

        switch (choice) {
            case 1:
                addAttendence();
                break;
            case 2:
                viewAttendence();
                break;
            case 3:
                System.out.print("Enter Emp id: ");
                int id = sc.nextInt();
                sc.nextLine();
                getEmpDetails(id);
                break;
            case 4:
                saveEmpData();
                break;
            case 5:
                System.out.println("Exiting...");
                break;
            default:
                System.out.println("Invalid choice! Try again.");
        }
    }

    public static void main(String[] args) {
        HR_manager hr = new HR_manager();
        hr.readEmpData(); // Load existing data at startup

        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            hr.start(); // Show menu and perform operation

            System.out.print("\nDo you want to continue? (1 for Yes / 0 for No): ");
            choice = sc.nextInt();
            sc.nextLine();
        } while (choice == 1);

        hr.saveEmpData(); // Save before exit
        sc.close();
        System.out.println("Program terminated.");
    }
}
