package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


public class Test {
    private ObservableList<Category> categories;
    private HashMap<String,Integer> resultaten;

    public Test() {
        categories = FXCollections.observableArrayList();
        resultaten = new HashMap<>();
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

    public void controlAnswer(String antwoord, int questionIndex) {
        Question vraag = getAllQuestions().get(questionIndex);
        String categorie = findCategory(vraag.getQuestion());
        if (vraag.getStatements().get(0).equals(antwoord)){
            if (resultaten.containsKey(categorie)){
                int currentScoreForCategorie = (int) resultaten.get(categorie);
                int nieuweScore = currentScoreForCategorie + 1;
                resultaten.replace(categorie,nieuweScore);
            } else {
                resultaten.put(categorie,1);
            }
        } else if (!resultaten.containsKey(categorie)) {
                resultaten.put(categorie,0);
            }

        }

    public String getResult() {
        //moet nog uitgewerkt worden - TB
        int totaleScore = 0;
        String categoryScores = "";
        Iterator it = resultaten.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            categoryScores += pair.getKey() + ":" + pair.getValue() + "\n";
            totaleScore = totaleScore + (int) pair.getValue();
            it.remove();
        }
        return " Total score: " + totaleScore + "\n" + categoryScores;
    }

    public String findCategory(String question){
        for (Category category: categories){
            for(Question questionObject : category.getQuestions()){
                if (questionObject.getQuestion().equals(question)){
                    return category.getName();
                }
            }
        }
        return "niet gevonden";
    }
}
