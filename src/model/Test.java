package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Evaluation.EvaluationContext;
import model.Evaluation.EvaluationFactory;
import model.State.ActiveTestState;
import model.State.InactiveTestState;
import model.State.State;

import java.util.*;


public class Test {
    private ObservableList<Category> categories;
    private String currentRightAnswer;
    private EvaluationContext evaluationContext;
    private Question currentQuestion;
    private ObservableList<String> previousScore;
    private ObservableList<Question> questions;

    State activeTestState;
    State inactiveState;

    State state;


    public Test() {
        questions = FXCollections.observableArrayList();
        categories = FXCollections.observableArrayList();
        previousScore = FXCollections.observableArrayList();
        evaluationContext = new EvaluationContext();
        activeTestState = new ActiveTestState(this);
        inactiveState = new InactiveTestState(this);


        state = inactiveState;
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
        questions.clear();
        for (Category category : categories) {
            for (Question question : category.getQuestions()) {
                questions.add(question);
            }
        }
        return questions;

    }

    // Methode voor editCategory
    public void editCategory(String oldName, String name, String description, String mainCategory) {
        state.editCategory(oldName,name,description,mainCategory);
        //State handelt het af - TB
    }

    public void editQuestion(String oldQuestion, String question, String category, String feedback, ObservableList<String> statements) {
        // Verwoede poging tot State-gebruik, geen idee of ik het hierachter juist heb gedaan? -MVV
        state.editQuestion(oldQuestion, question, category, feedback, statements);
    }

    public void addCategory(Category category) {
        state.addCategory(category);
        //=> State handelt het af - TB
        //categories.add(category);
    }

    public void addQuestionWithObservableList(String question, ObservableList<String> statements, String category, String feedback) {
        state.addQuestionWithObservableList(question,statements,category,feedback);
        this.getAllQuestions();
        // => State handelt het af - TB
    }

    // Redundante code, moet nog aangepast worden - MVV
    public void addQuestionWithArrayList(String question, ArrayList<String> statements, String category, String feedback) {
        state.addQuestionWithArrayList(question,statements,category,feedback);
        //=> State handelt het af - TB
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
        state.setEvaluationStrategy(list);
        //=> state handelt het af - TB
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
        this.previousScore.clear();
        for (String x : previousScore) {
            this.previousScore.add(x);
        }

    }

    public ObservableList<String> getPreviousScore() {
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

    public void setState(State state) {
        this.state = state;
    }

    public State getState() {
        return state;
    }

    public State getActiveTestState() {
        return activeTestState;
    }

    public State getInactiveState() {
        return inactiveState;
    }

    public EvaluationContext getEvaluationContext() {
        return evaluationContext;
    }

    public void modifyQuestion(String question, ObservableList<String> statements, String category, String feedback) {
        //vind de categorie die de originele question had
        // functie categorie x . modifyquestion
        state.modifyQuestion(question,statements,category,feedback);
    }
}
