/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import repository.PersonRepo;
import view.Menu;

/**
 *
 * @author Nguyen Quang Hau
 */
public class HandleFileProgramming extends Menu<String>{
    private PersonRepo personRepo;

    public HandleFileProgramming() {
        super("\n=======File Processing=======", new String[]{"Find person infor","Copy text to new file","Exit"});
        personRepo = new PersonRepo();
    }
    
    
    @Override
    public void excute(int n) {
        switch (n) {
            case 1:
                personRepo.findPersonBySalary();
                break;
           case 2:
                personRepo.copyTextToANewFile();
                break;
        }
    }
    
}
