package Employee.Basic;


import Employee.Database.DatabaseConnection;
import basics.Employee;

import java.sql.SQLOutput;
import java.util.Scanner;

public class EmployeeMain {
    public static String[] covertStringToNumberArray(String actualName){
        String[] str = new String[2];
        String str1="";
        String str2="";
        for (int i = 0; i < actualName.length(); i++){
            char c = actualName.charAt(i);
            if( '0' <= c && c <= '9' )
                str1=str1+c;
            if( ('a' <= c && c <= 'z')||('A' <= c && c <= 'Z') )
                str2=str2+c;
        }
        System.out.println(str1);
        System.out.println(str2);
        str[0]=str1;
        str[1]= str2;
        return str;
    }
    public static void main(String[] args) {
        String finalEmpId = null;
        DatabaseConnection employeeDatabase= new DatabaseConnection();
        String maxEmpId = employeeDatabase.getMaxEmpId();
        if(maxEmpId!=null){
            //EMP1009
            String[] receiveArray = covertStringToNumberArray(maxEmpId);
            if(receiveArray!=null){
                int number = Integer.parseInt(receiveArray[0]);
                number = number+3;
                finalEmpId = receiveArray[1]+number;
            }
        }else{
            finalEmpId = Employee.initial+Employee.nextId;
        }

        Scanner sc = new Scanner(System.in);
        System.out.println("enter name");
        String name = sc.next();
        //employee.setName(name);

        System.out.println("enter address");
        String address = sc.next();
        //employee.setAddress(address);

        System.out.println("enter salary");
        float salary = sc.nextFloat();
        //employee.setSalary(salary);


        basics.Employee employee = new Employee(finalEmpId,name,address,salary);
        System.out.println(employee.getEmpId());
        System.out.println(employee.getName());
        System.out.println(employee.getAddress());
        System.out.println(employee.getSalary());


        employeeDatabase.storeEmployeeInfo(employee);

        System.out.println( employee.getEmpId() + " is sucessfully added in database");
    }

}
