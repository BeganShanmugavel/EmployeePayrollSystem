import java.util.*;


public class Employee{
    int id;
    String name;
    double salary;
}
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Employee emp = new Employee();
        emp.id=101;
        emp.name="arun";
        emp.salary=200000;
        System.out.println(emp.name);
    }
}