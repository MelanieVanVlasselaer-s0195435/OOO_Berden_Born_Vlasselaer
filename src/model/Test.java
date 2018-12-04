package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;


public class Test {
    private ObservableList<Category> categories;


    public Test() {
        categories = FXCollections.observableArrayList();
    }


    public ObservableList<Category> getCategories() {
        return categories;
    }

    public ObservableList<Question> getAllQuestions() {
        ObservableList<Question> questions = FXCollections.observableArrayList();

        for (Category category : categories) {
            for (Question question : category.getQuestions()) {
                questions.add(question);
            }
        }
        return questions;

    }

    public void addCategory(Category category) {
        categories.add(category);
    }

    public void addQuestionWithObservableList(String question, ObservableList<String> statements, String category, String feedback) {
        int index = 0;
        boolean categoryExist = false;
        for (Category cat : categories) {

            if (cat.getName().equals(category)) {
                index = categories.indexOf(cat);
                categoryExist = true;
                break;
            }

        }


        if (categoryExist) {
            categories.get(index).addQuestionWithObservableList(question, statements, feedback);
        }
    }

    // Redundante code, moet nog aangepast worden - MVV
    public void addQuestionWithArrayList(String question, ArrayList<String> statements, String category, String feedback) {
        int index = 0;
        boolean categoryExist = false;
        for (Category cat : categories) {
            if (cat.getName().equals(category)) {
                index = categories.indexOf(cat);
                categoryExist = true;
                break;
            }

        }

        if (categoryExist) {
            categories.get(index).addQuestion(question, statements, feedback);
        }
    }

    public void makeCategories(ArrayList<String> elementen) {
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
        for (int i = 0; i < primitieveQuestions.size(); i += 4) {
            String question = primitieveQuestions.get(i);
            String category = primitieveQuestions.get(i + 1);
            String feedback = primitieveQuestions.get(i + 2);
            String primitieveStatements = primitieveQuestions.get(i + 3);
            ArrayList<String> statements = new ArrayList();
            for (String woord : primitieveStatements.split("\\s", 0)) {
                statements.add(woord);
            }

            addQuestionWithArrayList(question, statements, category, feedback);
        }
    }

}
