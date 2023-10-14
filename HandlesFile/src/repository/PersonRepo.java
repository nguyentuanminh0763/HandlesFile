/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import common.Validation;
import dao.PersonDao;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import model.Person;

/**
 *
 * @author Nguyen Quang Hau
 */
public class PersonRepo implements IPersonRepo {

    private Validation val = new Validation();

    public PersonRepo() {
    }

    @Override
    public void findPersonBySalary() {
        System.out.println("--------- Person info ---------");
        String path = val.getString("Enter Path: ");
        double salary = 0;

        // get salary
        try {
            Scanner sc = new Scanner(System.in);
            System.out.print("Enter money: ");
            salary = sc.nextDouble();
            if (salary < 0) {
                salary = 0;
            }
        } catch (Exception e) {
            salary = 0;
        }

        // get person who have salary >=  inputted Salary
        List<Person> list = new ArrayList<>();

        try {
            list = PersonDao.getInstance().getPersonListBySalary(salary, path);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        // get person who have max salary and min salary in the selected list
        List<Person> maxSalary = new ArrayList<>();
        List<Person> minSalary = new ArrayList<>();
        if (!list.isEmpty()) {
            System.out.println("he");
            maxSalary.add(list.get(0));
            minSalary.add(list.get(0));

            for (Person person : list) {
                if (person != list.get(0)) {
                    if (person.getSalary() > maxSalary.get(0).getSalary()) {
                        maxSalary.clear();
                        maxSalary.add(person);
                    } else if (person.getSalary() < minSalary.get(0).getSalary()) {
                        minSalary.clear();
                        minSalary.add(person);
                    } else if (person.getSalary() == maxSalary.get(0).getSalary() && maxSalary.get(0) == minSalary.get(0)) {
                        maxSalary.add(person);
                        minSalary.add(person);
                    } else if (person.getSalary() == maxSalary.get(0).getSalary()) {
                        maxSalary.add(person);
                    } else if (person.getSalary() == minSalary.get(0).getSalary()) {
                        minSalary.add(person);
                    }
                }
            }
        }

        display(list, maxSalary, minSalary);
    }

    private void display(List<Person> list, List<Person> maxSalary, List<Person> minSalary) {
        System.out.println("------------Result-----------");
        System.out.printf("%-15s%-15s%-15s\n", "Name", "Address", "Money");

        Collections.sort(list);
        list.forEach(person -> System.out.printf("%-15s%-15s%-15s\n", person.getName(), person.getAddress(), person.getSalary()));

        System.out.print("\nMax: ");
        maxSalary.forEach(person -> {
            if (maxSalary.lastIndexOf(person) != maxSalary.size() - 1) {
                System.out.print(person.getName() + ", ");
            } else {
                System.out.print(person.getName());
            }
        });

        System.out.print("\nMin: ");
        minSalary.forEach(person -> {
            if (minSalary.lastIndexOf(person) != minSalary.size() - 1) {
                System.out.print(person.getName() + ", ");
            } else {
                System.out.print(person.getName());
            }
        });

    }

    public void copyTextToANewFile() {
        String srcPath = val.getString("Enter source: ");
        String destPath = val.getString("Enter destination: ");
        try {
            PersonDao.getInstance().copyToNewFile(srcPath, destPath);
            System.out.println("Copied");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
