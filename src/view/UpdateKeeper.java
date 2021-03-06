/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import animal.Animal;
import keeper.Keeper;


/**
 *
 * @author fernandoms
 */
public class UpdateKeeper extends View {

    private Keeper keeper;
    public UpdateKeeper(Keeper k){
        this.keeper = k;
    }
    @Override
    public void display() {
        System.out.println("====================================================");
        System.out.println();
        System.out.println("Update keeper info:");
        System.out.println();
        System.out.println(keeper.shortInfo());
        System.out.println();

        for(String aExhibitNumber:keeper.getAnimalsAssigned()){
            if(aExhibitNumber!=null){
            System.out.println("  "+ animal().searchByExhibitNumber(aExhibitNumber).shortInfo());}
        }

        System.out.println();
        System.out.println("1-Add Qualifications");
        System.out.println("2-Assign Animal");
        System.out.println("3-Unassign Animal");
        System.out.println();
        System.out.println("0-Return");
        System.out.println();
    }
       

    @Override
    public View getOpt() {
        
        switch(getInputWithLabel("Option")){
            
            case "1":
                if(keeper.getAmountOfAnimals()!=0){
                    err("This keeper must have no assigned Animal to change its qualification.");
                    return new UpdateKeeper(this.keeper);
                }else if(keeper.getAmountOfQualification() == 3){
                    err("keeper aready have 3 qualifications.");
                    return new UpdateKeeper(this.keeper);
                }                
                return new AddQualification(this.keeper);
            case "2":
                Animal result = animal().searchByExhibitNumber(getInputWithLabel("Exhibit Number"));
                if(result == null){
                    err("No Animal found with the given Exhibition Number");
                    return this;
                }else if(!keeper.isAvailable()){
                    err("Can't assign the animal because keeper has already 10 animals to care for.");
                }else if(!keeper.hasQualification(result.getType())){
                     err("Can't assign the animal because keeper has no qualification for this type");
                }else if(!keeper.assignAnimal(result)){
                    err("Can't assign the animal because this animals has already been assigned to this Keeper");
                }
                return new UpdateKeeper(this.keeper);  
            case "3":
                if(!keeper.unAssignAnimal(getInputWithLabel("Exhibit Number"))){
                    err("No Animal found with the given Exhibition Number");
                }
                
                return new UpdateKeeper(this.keeper); 
            case "0":
                return new ShowKeeper(this.keeper);
            default:
                err("Unable to indentify the typed option. Please try again.");
                return new UpdateKeeper(this.keeper);
        }
    }
    
}
