package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;


public class Test {
    private ObservableList<Category> categories;
    private ObservableList<Question> questions;



    public Test () {
        categories = FXCollections.observableArrayList();
        questions = FXCollections.observableArrayList();
    }


    public ObservableList<Category> getCategories(){
        return categories;
    }

    public ObservableList<Question> getQuestions() {
        return questions;
    }

    public void addCategory(Category category){
        categories.add(category);
    }

    public void addQuestion(Question question){
        questions.add(question);
    }

    public void makeCategories(ArrayList<String> elementen) {
        /*try {
            for(int i = 0; i< elementen.size();i+=3){
                String naam = elementen.get(i);
                String description = elementen.get(i+1);
                String hoofdCategorie = elementen.get(i+2);
                if (hoofdCategorie.equals("null")){
                    Category category = new Category(naam,description);
                    categories.add(category);
                } else {
                    for (Category x : categories) {
                        if (x.getName().equals(hoofdCategorie)) {
                            Category geselecteerdeHoofdCategorie = x;
                            Category category = new Category(naam, description, geselecteerdeHoofdCategorie);
                            categories.add(category);
                        }
                    }
                }
                //errorlijn in de toekomst voorzien met try-catch en domainclass - TB
                Category category = new Category(naam,description);
            }
        } catch (Exception e) {
            throw new ModelException("Categorie aanmaken is niet gelukt");
        }*/

        try {
            for (int i = 0; i < elementen.size(); i += 3) {
                String naam = elementen.get(i);
                String description = elementen.get(i + 1);
                String hoofdCategorie = elementen.get(i + 2);
                if (hoofdCategorie.equals("null")) {
                    Category category = new Category(naam, description);
                    categories.add(category);
                } else {
                    Category category = new Category(naam, description, hoofdCategorie);
                    categories.add(category);
                }
            }
        } catch (Exception e) {
            throw new ModelException("Categorie aanmaken is niet gelukt");
        }
    }


    public void makeQuestions(ArrayList<String> primitieveQuestions) {
        ArrayList <String> statements = new ArrayList();
        for (int i = 0; i < primitieveQuestions.size();i+=4) {
            String question = primitieveQuestions.get(i);
            String category = primitieveQuestions.get(i+1);
            String feedback = primitieveQuestions.get(i+2);
            String primitieveStatements = primitieveQuestions.get(i+3);
            for (String woord:primitieveStatements.split("\\s",0)) {
                statements.add(woord);
            }
            Question x = new Question(question, statements, category, feedback);
            questions.add(x);
        }
    }

}
