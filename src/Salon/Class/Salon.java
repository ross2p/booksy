package Salon.Class;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Salon {
    final String name;
    List<Employee> employees;
    final String address;
    final List<Days> workingDays;           // Map<Days, String> до кожного дня має бути година яка
    private static int counter = 0;         // Мені здадється це не треба

    public Salon(String name, List<Employee> employees, String address, List<Days> workingDays) {
        this.name = name;
        this.employees = employees;
        this.address = address;
        this.workingDays = workingDays;
    }
    public Salon(String name, Employee employee, String address, List<Days> workingDays) {
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


    public List<Days> getWorkingDays() {
        return workingDays;
    }

    @Override
    public String toString() {
        counter++;
        return "Salon number " + counter + " {" + " Name: " + name + "  ;\nEmployees: " + employees + "  ;\nAddress: "
                + address + "\nWorkingDays: " + workingDays + " }\n\n";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Salon salon)) return false;
        return Objects.equals(name, salon.name) && Objects.equals(address, salon.address) && Objects.equals(workingDays, salon.workingDays);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, address, workingDays);
    }

    public void  addEmployees(Salon newElement){
        for (Employee newEmployee: newElement.getEmployees()){
            boolean isRepeated = false;
            for (Employee e:employees){
                if (e.equals(newEmployee)){
                        isRepeated = true;
                        e.addServices(newEmployee.getServices());
                }
            }
            if (!isRepeated){
                employees.add(newEmployee);
            }
        }
    }
}