

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Employee implements Serializable {
    private int id;
    private String name;
    private List<String> projects;

    public Employee(int id, String name) {
        this.id = id;
        this.name = name;
        this.projects = new ArrayList<>();
    }

    public int getId() { return id; }
    public String getName() { return name; }

    public void addProject(String project) {
        projects.add(project);
    }

    public List<String> getProjects() {
        return projects;
    }

    @Override
    public String toString() {
        return "Employee ID: " + id + ", Name: " + name + ", Projects: " + projects;
    }

}