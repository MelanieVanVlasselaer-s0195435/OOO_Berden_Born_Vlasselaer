package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;


public class Test {
    private ObservableList<Category> categories;
    private ObservableList<Question> questions;
    private ArrayList<Question> questionArray;



    public Test () {
        categories = FXCollections.observableArrayList();
        questions = FXCollections.observableArrayList();
        questionArray = new ArrayList<>();
    }


    public ObservableList<Category> getCategories(){
        return categories;
    }

    public ObservableList<Question> getQuestions() {
        /*System.out.println("Ik print de lijst waar de 2 objecten inzitten met dezelfde tostring methode");
        for (Question x : questions) {
            System.out.println(x.toString());
        }
        */
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
                Category category = null;
                if (hoofdCategorie.equals("null")) {
                    category = new Category(naam, description);
                } else {
                    category = new Category(naam, description, hoofdCategorie);
                }
                categories.add(category);
            }
        } catch (Exception e) {
            throw new ModelException("Categorie aanmaken is niet gelukt");
        }
    }


    public void makeQuestions(ArrayList<String> primitieveQuestions) {
        //System.out.println("ik print de 2 objecten die ik aanmaak met tostring functie van dat object");
        ArrayList <String> statements = new ArrayList();
        for (int i = 0; i < primitieveQuestions.size();i+=4) {
            String question = primitieveQuestions.get(i);
            String category = primitieveQuestions.get(i+1);
            String feedback = primitieveQuestions.get(i+2);
            String primitieveStatements = primitieveQuestions.get(i+3);
            //statement lijst dient leeg gemaakt worden anders krijgt de 2de vraag ook de statements van de eerste vraag!! -FB
            statements.clear();
            for (String woord:primitieveStatements.split("\\s",0)) {
                statements.add(woord);
            }
            Question x = new Question(question, statements, category, feedback);
            System.out.println("object");
            System.out.println(x.toString());
            questions.add(x);
            questionArray.add(x);
            System.out.println("observableList");
            for (Question y : questions) {
                System.out.println(y.toString());
            }
            System.out.println("array");
            for (Question y : questionArray) {
                System.out.println(y.toString());
            }
            System.out.println(" ");

        }
    }

}
