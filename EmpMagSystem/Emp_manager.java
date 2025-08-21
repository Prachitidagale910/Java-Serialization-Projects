

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Emp_manager {

    private List<Employee> employees = new ArrayList<>();
    private Scanner sc = new Scanner(System.in);

    // Add a new employee
    public void addEmployee() {
        System.out.print("Enter Employee ID: ");
        int id = sc.nextInt();
        sc.nextLine(); // consume leftover newline
        System.out.print("Enter Employee Name: ");
        String name = sc.nextLine();
        employees.add(new Employee(id, name));
        System.out.println("Employee added successfully!");
    }

    // Add project to an existing employee
    public void addProjectToEmployee() {
        System.out.print("Enter Employee ID: ");
        int id = sc.nextInt();
        sc.nextLine(); // consume leftover newline

        Employee emp = findEmployeeById(id);
        if (emp != null) {
            System.out.print("Enter Project Name: ");
            String project = sc.nextLine();
            emp.addProject(project);
            System.out.println("Project added successfully!");
        } else {
            System.out.println("Employee not found!");
        }
    }

    // View all employees with their projects
    public void viewAllEmployees() {
        if (employees.isEmpty()) {
            System.out.println("No employees found!");
            return;
        }

        for (Employee emp : employees) {
            System.out.println(emp); // calls Employee.toString()
        }
    }

    // Helper method to find employee by ID
    private Employee findEmployeeById(int id) {
        for (Employee emp : employees) {
            if (emp.getId() == id) {
                return emp;
            }
        }
        return null;
    }

    // Menu-driven system
    public void start() {
        int choice;
        do {
            System.out.println("\n--- Employee Management System ---");
            System.out.println("1. Add Employee");
            System.out.println("2. Add Project to Employee");
            System.out.println("3. View All Employees");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine(); // consume leftover newline

            switch (choice) {
                case 1:
                    addEmployee();
                    break;
                case 2:
                    addProjectToEmployee();
                    break;
                case 3:
                    viewAllEmployees();
                    break;
                case 4:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice!");
            }
        } while (choice != 4);
    }

    public static void main(String[] args) {
        Emp_manager manager = new Emp_manager();
        manager.start();
    }
}
