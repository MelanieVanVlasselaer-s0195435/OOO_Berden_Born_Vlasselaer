package model;

import database.CategoryText;
import database.DBcontext;

import java.util.ArrayList;

public class ModelFacade {
ArrayList <Category> categories = new ArrayList();
ArrayList <Question> questions = new ArrayList();


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
        for (Category x : categories){
            if (x.getName().equals(mainCategory)){
                toAdd = x;
            }
        }
        Category newCategory = new Category(name, description, toAdd);
        categories.add(newCategory);
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

    public ArrayList<Question> getQuestions() {
        return questions;
    }

    public void addQuestion(String question, ArrayList<String> statements, String category, String feedback){
        Question x = new Question(question, statements, category, feedback);
        questions.add(x);
    }
}
