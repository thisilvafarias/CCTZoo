/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import animal.Animal;
import ultility.Date;
/**
 *
 * @author Fernando Marinho da Silva
 * 
 */
public class AnimalMenu extends View{

    @Override
    public void display() {
        System.out.println("====================================================");
        System.out.println();
        System.out.println("Animal Menu:");
        System.out.println();
        System.out.println("1-Search Menu");
        System.out.println("2-List Animals");
        System.out.println("3-Update Animal");
        System.out.println("4-Add Animal");
        System.out.println();
        System.out.println("9-Return");
        System.out.println("0-Exit");
        System.out.println();
    }

    @Override
    public View getOpt() {
        Animal result;
        Animal[] resultList;
        switch(getInputWithLabel("Option")){
            case "1":
                return new SearchAnimalMenu();
            case "2":
                resultList = animal().all();
                return new ShowAnimalsList(resultList);
            case "3":
                result = animal().searchByExhibitNumber(getInputWithLabel("Exhibit Number"));
                if(result == null){
                    err("No Animal found with the given Exhibition Number");
                    return this;
                }
                return new UpdateAnimal(result); 
            case "4":
                String specie = getInputWithLabel("Specie");
                Date birthDate = new Date(getInputWithLabel("Birth date (yyyy-mm-dd)"));
                Date arrivalDate = new Date(getInputWithLabel("Arrival date (yyyy-mm-dd)"));
                String gender;
                do{
                    gender = getInputWithLabel("Gender");
                    if(!(gender.equalsIgnoreCase("female") || gender.equalsIgnoreCase("male"))){
                        gender="";
                        err("Unable to identify the typed gender.");
                        
                    }
                }while(gender.equals(""));
                
                Animal animal = animalFactory().create(specie, birthDate, arrivalDate, gender);
                animal().save(animal);
                return new UpdateAnimal(animal);
                
            case "9":
                return new MainMenu();
            case "0":
                return null;
            default:
                err("Unable to indentify the typed option. Please try again");
                return new AnimalMenu();
        }
    }
    
}
