package ru.geekbrains;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

public class Main {

    private static Random random = new Random();

    /**
     * TODO 2. Переработать метод generateEmployee(). Метод должен возвращать
     * любого случайного сотрудника разного типа (Worker, Freelancer).
     * @return
     */
    public static Employee generateEmployee(){
        String[] names = new String[]{"Анатолий", "Глеб", "Клим", "Мартин", "Лазарь", "Владлен", "Клим", "Панкратий", "Рубен", "Герман"};
        String[] surnames = new String[]{"Григорьев", "Фокин", "Шестаков", "Хохлов", "Шубин", "Бирюков", "Копылов", "Горбунов", "Лыткин", "Соколов"};
        int salary = random.nextInt(900, 1500);
        int yearOfBirth = random.nextInt(1960, 2005);

        if(random.nextInt(1, 3) == 1){
            int salaryIndex = random.nextInt(28, 31);
            return new Worker(names[random.nextInt(10)], surnames[random.nextInt(10)], salary*salaryIndex, yearOfBirth);
        } else{
            int salaryIndex = random.nextInt(37, 40);
            return new Freelancer(names[random.nextInt(10)], surnames[random.nextInt(10)], salary*salaryIndex, yearOfBirth);
        }
    }


    public static void main(String[] args) {

//        Worker worker = new Worker("Глеб", "Григорьев", 120000);
//        System.out.println(worker);

        Employee[] employees = new Employee[10];
        for (int i = 0; i < employees.length; i++) {
            employees[i] = generateEmployee();
        }

        for (Employee employee: employees) {
            System.out.println(employee);
        }

//        Arrays.sort(employees, new SalaryComparator());
//        Arrays.sort(employees, new SurNameComparator());
//        Arrays.sort(employees);
        Arrays.sort(employees, new YearOfBithComparator());

        System.out.println("\n*** Отсортированный массив сотрудников ***\n");

        for (Employee employee: employees) {
            System.out.println(employee);
        }
    }
}

/**
 * TODO 3. Создать свой дополнительный компаратор, можно добавить дополнительные состояния
 * по которым будет производится сравнение.
 */
class SalaryComparator implements Comparator<Employee>{

    // 1 0 -1
    @Override
    public int compare(Employee o1, Employee o2) {
        return Double.compare(o2.calculateSalary(), o1.calculateSalary());
//        return o1.calculateSalary() == o2.calculateSalary() ? 0 :
//                (o1.calculateSalary() > o2.calculateSalary() ? 1 : -1);
    }
}

class SurNameComparator implements Comparator<Employee>{

    @Override
    public int compare(Employee o1, Employee o2) {
        return o1.surName.compareTo(o2.surName);
    }
}

class YearOfBithComparator implements Comparator<Employee>{
    @Override
    public int compare(Employee o1, Employee o2) {
        return Integer.compare(o1.yearOfBirth, o2.yearOfBirth);
    }
}

abstract class Employee implements Comparable<Employee>{

    protected String firstName;
    protected String surName;
    protected double salary;
    protected int yearOfBirth;

    public Employee(String firstName, String surName, double salary, int yearOfBirth) {
        this.firstName = firstName;
        this.surName = surName;
        this.salary = salary;
        this.yearOfBirth = yearOfBirth;
    }

    public abstract double calculateSalary();

    @Override
    public String toString() {
        return String.format("Сотрудник: %s %s; Ставка: %.2f; Среднемесячная заработная плата: %.2f; Год рождения: %d",
                surName, firstName, salary, calculateSalary(), yearOfBirth);
    }

    @Override
    public int compareTo(Employee o) {
        return Double.compare(calculateSalary(), o.calculateSalary());
    }
}

class Worker extends Employee{

    public Worker(String firstName, String surName, double salary, int yearOfBirth) {
        super(firstName, surName, salary, yearOfBirth);
    }

    @Override
    public double calculateSalary() {
        return salary;
    }

    @Override
    public String toString() {
        return String.format("%s %s; Рабочий; Среднемесячная заработная плата: %.2f; Год рождения: %d",
                surName, firstName, calculateSalary(), yearOfBirth);
    }
}

/**
 * TODO: 1. Разработать самостоятельно в рамках домашней работы.
 */
class Freelancer extends Employee{
    private static Random random = new Random();
    int workingHours = random.nextInt(8, 176);
    public Freelancer(String firstName, String surName, double salary, int yearOfBirth) {
        super(firstName, surName, salary, yearOfBirth);
    }

    @Override
    public double calculateSalary() {
        return salary/176*workingHours;
    }

    @Override
    public String toString() {
        return String.format("%s %s; Фрилансер; Заработная плата: %.2f; Заработная плата за месяц: %.2f; Год рождения: %d",
                surName, firstName, salary, calculateSalary(), yearOfBirth);
    }
}
