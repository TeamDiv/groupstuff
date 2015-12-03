package com.company;
import javax.lang.model.element.NestingKind;
import java.util.Scanner;

public class Main {

    public static void main(String[] args){

        Scanner keyboard = new Scanner(System.in);
        String input;
        int num_employees;

        System.out.println("How many employees do you have?");
        num_employees = keyboard.nextInt();

        Employees employees [] = new Employees[num_employees];


        for(int i = 0; i < num_employees; i++){

            System.out.println("Are you paid hourly or salary? \nif you are paid Hourly type (h)\nif you are paid by Salary type (s)");
            input = keyboard.next();

            if(input.equalsIgnoreCase("h")){
                System.out.println("What is your first name?");
                String fname = keyboard.next();
                System.out.println("What is your last name?");
                String lname = keyboard.next();
                System.out.println("What is your hourly rate?");
                double hourly = keyboard.nextDouble();
                System.out.println("How many hours do you work?");
                double hours = keyboard.nextDouble();
                System.out.println("What is your occupation?");
                String job = keyboard.next();
                employees[i] = new Hourly(fname, lname, job, hourly, hours);
            }
            if (input.equalsIgnoreCase("s")){
                System.out.println("What is your first name?");
                String fname = keyboard.next();
                System.out.println("What is your last name?");
                String lname = keyboard.next();
                System.out.println("What is your salary?");
                double salary = keyboard.nextDouble();
                System.out.println("What is your occupation?");
                String job = keyboard.next();
                employees[i] = new Salary(fname, lname, salary, job);
            }
            if ((!input.equalsIgnoreCase("s")) && (!input.equalsIgnoreCase("h"))){
                System.out.print("This is not a valid answer, please input again. ");
                i--;
                System.out.println(input);
            }

        }

        //Sort
        boolean flag = true;
        int k = 0;
        while(flag == true && k < num_employees){
            flag = false;
            for(int l = 0; l < num_employees - k - 1; k++){
                if(employees[k].get_employee_id().substring(0, 1).compareTo(employees[k+1].get_employee_id().substring(0, 1)) > 0){
                    Employees temp;
                    temp = employees[k];
                    employees[k] = employees[k+1];
                    employees[k+1] = temp;
                    flag = true;
                }
            }
            k++;
        }

        for(int index = 0; index < num_employees; index++){
            System.out.println(employees[index].toString());
            System.out.println();
        }

        System.out.println("Employees recorded.");
    }
}

class Hourly extends Employees {

    private double hourly_rate;
    private double hours;
    double salary;

    Hourly(String f_name, String l_name, String occ, double hr, double h){
        super(f_name, l_name, occ);
        hourly_rate = hr;
        hours = h;
        salary(hourly_rate, hours);
    }

    public double salary(double hr, double h){
        salary = hr * h;
        return salary;
    }

    public String toString(){
        return super.toString() + "\nSalary: $" + salary;
    }
}

class Salary extends Employees{
    private static String job;
    double salary;

    Salary(String f_name, String l_name,  double salary, String job){
        super(f_name, l_name, job);
        this.salary = salary;

    }

   public String toString(){
        return super.toString() + "\nSalary: $" + salary;
    }
}

abstract class Employees {

    private String employee_id;
    private String first_name;
    private String last_name;
    private String occupation;
    private String id;
    private static int count;

    Employees(String fname, String lname, String occ){
        first_name = fname;
        last_name = lname;
        occupation = occ;
        count++;
        set_employee_id(fname, lname);
    }

    public void set_employee_id(String fname, String lname){
        String f_initial =  fname.substring(0, 1);
        String l_initial = lname.substring(0, 1);
        id = f_initial + l_initial + "00" + count;
        employee_id = id;
    }

    public String get_employee_id(){
        return employee_id;
    }

    public String toString(){
        return "Employee Id: "+ employee_id + "\nName: "
                + first_name + " " + last_name + "\nOccupation: " + occupation;
    }

}