package model;

import database.CategoryText;
import database.DBcontext;

import java.util.ArrayList;

public class ModelFacade {
ArrayList <Category> categories = new ArrayList();


    public ModelFacade(){


    }

    public void makeCategories(ArrayList<String> elementen){
        for(int i = 0; i< elementen.size();i+=3){
            String naam = elementen.get(i);
            String description = elementen.get(i+1);
            String hoofdCategorie = elementen.get(i+2);
            if (hoofdCategorie.equals("null")){
                Category category = new Category(naam,description);
                categories.add(category);
            } else {
                for (Category x : categories){
                    if (x.getName().equals(hoofdCategorie)){
                        Category geselecteerdeHoofdCategorie = x;
                        Category category = new Category(naam,description,geselecteerdeHoofdCategorie);
                        categories.add(category);
                    }
                }
                //errorlijn in de toekomst voorzien met try-catch en domainclass - TB
                Category category = new Category(naam,description);
            }
        }
    }

    public ArrayList getCategories(){
        return categories;
    }

    public void addCategory(String name, String description, String mainCategory){
        Category toAdd = null;
        /*for (Object x : categories){
            if (x.getName().equals(mainCategory)){
                toAdd = x;
            }
        }
        Category newCategory = new Category(name, description, toAdd);
        categories.add(newCategory);
*/
    }


}
