package Salon.Class;

import java.util.*;

public class Salon {
    private String name;
    Set<Employee> employees;
    private String address;
    private Map<Days, String> workingDays;           // Map<Days, String> до кожного дня має бути година

    public Salon(String name, Set<Employee> employees, String address, Map<Days, String> workingDays) {
        this.name = name;
        this.employees = employees;
        this.address = address;
        this.workingDays = workingDays;
    }

    public Salon(String name, Employee employee, String address, Map<Days, String> workingDays) {
        this.name = name;
        this.employees = new HashSet<>();
        employees.add(employee);
        this.address = address;
        this.workingDays = workingDays;
    }

    public String getName() {
        return name;
    }

    public Set<Employee> getEmployees() {
        return employees;
    }

    public String getAddress() {
        return address;
    }


    public Map<Days, String> getWorkingDays() {
        return workingDays;
    }

    @Override
    public String toString() {
        StringBuilder HoursOfWorkToStr = new StringBuilder();
        HoursOfWorkToStr.append(String.format("\n+------------+-------------+"));
        HoursOfWorkToStr.append(String.format("\n| %-10s | %-11s |", "Day", "HoursOfWork"));
        HoursOfWorkToStr.append(String.format("\n+------------+-------------+"));

        for (Map.Entry<Days, String> entry : workingDays.entrySet()) {
            HoursOfWorkToStr.append(String.format("\n| %-10s | %-11s |", entry.getKey(), entry.getValue()));
        }
        HoursOfWorkToStr.append(String.format("\n+------------+-------------+"));

        return "\nName: " + name + '\n' +
                "Address: " + address +  /*'\n' +
                "HoursOfWork: " + */ HoursOfWorkToStr.toString() + '\n' ;
    }

    @Override
    //По назві салону
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Salon salon)) return false;
        return Objects.equals(name, salon.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    public void addEmployees(Salon newElement) {
        for (Employee newEmployee : newElement.getEmployees()) {
            boolean isRepeated = false;
            for (Employee e : employees) {
                if (e.equals(newEmployee)) {
                    isRepeated = true;
                    e.addServices(newEmployee.getServices());
                }
            }
            if (!isRepeated) {
                employees.add(newEmployee);
            }
        }
    }
    public Employee getEmployee(String name){
        for (Employee e: employees){
            if (e.getName().equals(name)){
                return e;
            }
        }
        return null;
    }
}