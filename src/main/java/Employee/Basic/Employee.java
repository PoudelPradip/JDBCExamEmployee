
package basics;

public class Employee {
    public static int  nextId=  1009;
    public   static final String initial = "EMP";

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    private String empId;

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\n' +
                ", address='" + address + '\n' +
                ", salary=" + salary +
                '}';
    }

    private  String name;
    private String address;
    private float salary;

    public Employee( String empId,String name, String address, float salary) {
        this.empId = empId;
        this.name = name;
        this.address = address;
        this.salary = salary;
        nextId+=3;
    }

    public  Employee(){

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public float getSalary() {
        return salary;
    }

    public void setSalary(float salary) {
        this.salary = salary;
    }

}
