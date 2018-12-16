package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Evaluation.EvaluationContext;
import model.Evaluation.EvaluationFactory;
import model.Evaluation.Feedback;
import model.Evaluation.Score;

import java.util.*;


public class Test {
    private ObservableList<Category> categories;
    private HashMap<String, int[]> resultaten;
    private String currentRightAnswer;
    private EvaluationContext evaluationContext;
    private Question currentQuestion;
    private ArrayList<String> previousScore;

    public Test() {
        categories = FXCollections.observableArrayList();
        evaluationContext = new EvaluationContext();
    }


    public ObservableList<Category> getCategories() {
        return categories;
    }

    public Question getCurrentQuestion() {
        return currentQuestion;
    }

    public String getCurrentRightAnswer() {
        return currentRightAnswer;
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

    public void editCategory(String oldName, String name, String description, String mainCategory) {
        int index = 0;

        for (Category x : categories) {
            if (x.getName().equals(oldName)) {
                Category category = new Category(name, description, mainCategory);
                categories.set(index, category);
                break;
            }

            index++;
        }
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
        currentQuestion = getAllQuestions().get(questionIndex);

        String categorie = findCategory(currentQuestion.getQuestion());

        if (!antwoord.equals(currentRightAnswer)) {
            evaluationContext.setNextResult();
        }
    }

    public String findCategory(String question) {
        for (Category category : categories) {
            for (Question questionObject : category.getQuestions()) {
                if (questionObject.getQuestion().equals(question)) {
                    return category.getName();
                }
            }
        }
        return "niet gevonden";
    }

    public Category findCategoryObject(String name) {
        for (Category category : categories) {
            if (category.getName().equals(name)) {
                return category;
            }
        }
        return null;
    }

    public LinkedList<String> getNextQuestion(int questionIndex) {
        if (questionIndex >= this.getAllQuestions().size()) {
            System.out.println("error");
        } else {
            LinkedList<String> nextQuestion = new LinkedList<>();
            nextQuestion.add(this.getAllQuestions().get(questionIndex).getQuestion());
            ArrayList<String> statements = this.getAllQuestions().get(questionIndex).getStatements();
            currentRightAnswer = statements.get(0);
            ArrayList<String> shuffledList = new ArrayList<>();
            for (String x : statements) {
                shuffledList.add(x);
            }
            Collections.shuffle(shuffledList);
            for (String x : shuffledList) {
                nextQuestion.add(x);
            }
            return nextQuestion;
        }
        return null;
    }

    public void setEvaluationStrategy(ArrayList<String> list) {


        //array aanmaken die voor elke categorie standaard 10/10 geeft (de 10 is gelijk aan het aantal vragen dat een categorie heeft) -FB
        resultaten = new HashMap<>();
        for (Category x : this.getCategories()) {
            int[] Array = new int [2];
            Array[0] =  x.getQuestions().size();
            Array[1] =  x.getQuestions().size();
            resultaten.put(x.getName(), Array);
        }
        EvaluationFactory strategyFactory = new EvaluationFactory();
        evaluationContext.setEvaluationStrategy(strategyFactory.createStrategy(list.get(0), this, resultaten));

        /* - Vervangen door factory/reflection
        if (list.get(0).equals("SCORE")) {
            evaluationContext.setEvaluationStrategy(new Score(this, resultaten));
        }
        else {
            evaluationContext.setEvaluationStrategy(new Feedback(this));
        }
        */
    }

    public ArrayList<String> getResult() {
        return evaluationContext.getEvaluation();
    }

    public ArrayList<String> getCategoryElements() {
        ArrayList<String> categorieElements = new ArrayList<>();
        for (Category x : categories) {
            categorieElements.add(x.getName());
            categorieElements.add(x.getDescription());
            categorieElements.add(x.getHoofdcategorie());
        }
        return categorieElements;
    }

    public void setPreviousScore(ArrayList<String> previousScore) {
        this.previousScore = previousScore;
    }

    public ArrayList<String> getPreviousScore() {
        return previousScore;
    }

    public ArrayList<String> getQuestionElements() {
        ArrayList<String> questionElements = new ArrayList<>();
        for (Category x : categories) {
            List<Question> questions = x.getQuestions();
            for (Question y : questions) {
                questionElements.add(y.getQuestion());
                questionElements.add(x.getName());
                questionElements.add(y.getFeedback());
                String statements = "";
                for (String z : y.getStatements()) {
                    statements += z + " ";
                }
                questionElements.add(statements);
            }
        }
        return questionElements;
    }

    public List<String> getEvaluationMethods() {
        return evaluationContext.getList();
    }
}
