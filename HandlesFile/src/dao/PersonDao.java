/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
import model.Person;

/**
 *
 * @author Nguyen Quang Hau
 */
public class PersonDao {

    public static PersonDao instance;

    public static PersonDao getInstance() {
        if (instance == null) {
            synchronized (PersonDao.class) {
                if (instance == null) {
                    instance = new PersonDao();
                }
            }
        }
        return instance;
    }

    public List<Person> selectAll(String path) throws Exception {
          return readFromFile(path);
    }

    public List<Person> getPersonListBySalary(double salary, String path) throws Exception{
        List<Person> list = selectAll(path);
        List<Person> salaryList = new ArrayList<>();
        
        for (Person person : list) {
            if(person.getSalary() >= salary){
                salaryList.add(person);
            }
        }
        
        return salaryList;
    }
    
    private List<Person> readFromFile(String path) throws Exception {
        File file = new File(path);
        List<Person> list = new ArrayList<>();

        if (!file.exists()) {
            throw new Exception("Path does not exist");
        }
        if (!file.canRead()) {
            throw new Exception("Can not read file");
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String data;
            while ((data = br.readLine()) != null) {
                String[] token = data.split(";");
                list.add(new Person(token[0], token[1], token[2], Double.parseDouble(token[3])));
            }
        } catch (IOException e) {
            System.err.println("Reading the file error");
        }

        return list;
    }

    public void copyToNewFile(String srcPath, String destPath) throws Exception {
        File src = new File(srcPath);
        File des = new File(destPath);
        
        if(!src.exists()) throw new Exception("Path "+srcPath+" does not exist");
        if(!des.exists()) des.createNewFile();
        if(!src.canRead()) throw new Exception("Can not read file");
        if(!des.canWrite()) throw new Exception("Path does not exist");
        Files.copy(src.toPath(), des.toPath(), StandardCopyOption.REPLACE_EXISTING);
    }
   

}
