package Salon.Class;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Salon {
    private String name;
    List<Employee> employees;
    private String address;
    private Map<Days, String> workingDays;           // Map<Days, String> до кожного дня має бути година

    public Salon(String name, List<Employee> employees, String address, Map<Days, String> workingDays) {
        this.name = name;
        this.employees = employees;
        this.address = address;
        this.workingDays = workingDays;
    }

    public Salon(String name, Employee employee, String address, Map<Days, String> workingDays) {
        this.name = name;
        this.employees = new ArrayList<>();
        employees.add(employee);
        this.address = address;
        this.workingDays = workingDays;
    }

    public String getName() {
        return name;
    }

    public List<Employee> getEmployees() {
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Salon salon)) return false;
        return Objects.equals(name, salon.name) && Objects.equals(address, salon.address) && Objects.equals(workingDays, salon.workingDays);
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
}