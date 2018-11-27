package controller;

import Database.CategoryText;
import model.Category;

import java.util.ArrayList;


// Moet dit een singleton zijn? -TB

public class Quizcontroller {
    ArrayList<Category> categories;
    /*Category a = new Category("Design principles","the solid design principles");
    Category b = new Category("Design pattern","design patterns discussed this year");
    Category c = new Category("Java","Java extras");
    Category d = new Category("UML","Technique of drawing a class diagram");
    */
    CategoryText text = new CategoryText();

    public Quizcontroller(){
        categories = text.getCategories();
    }

    public ArrayList<Category> getCategories(){
        if (categories == null) {
            System.out.println("leeg");
            return null;
        }
        else {
            return categories;
        }
    }

}
