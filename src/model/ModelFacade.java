package model;

import database.CategoryText;
import database.DBcontext;

import java.util.ArrayList;

public class ModelFacade {
    DBcontext context = new DBcontext();
    ArrayList <Category> categories = new ArrayList();

    public ModelFacade(){
        context.setDBStrategy(new CategoryText());
        categories = context.load();
    }

    public ArrayList getCategories(){
        return categories;
    }

    public void addCategory(String name, String description, String mainCategory){
        Category toAdd = new Category("niks","niks");
        for (Category x : categories){
            if (x.getName().equals(mainCategory)){
                toAdd = x;
            }
        }
        Category newCategory = new Category(name, description, toAdd);
        categories.add(newCategory);

    }


}
