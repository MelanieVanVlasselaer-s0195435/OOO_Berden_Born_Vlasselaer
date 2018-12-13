package database;

import java.util.ArrayList;

public class DatabaseFacade {
    DBcontext context = new DBcontext();

    public DatabaseFacade(){
    }

    public ArrayList<String> loadCategorieElementen() {
        context.setDBStrategy(new CategoryText());
        return context.load();
    }

    public ArrayList<String> loadQuestionElementen(){
        context.setDBStrategy(new QuestionText());
        return context.load();
    }

    public ArrayList<String> loadEvalutionStrategy() {
        context.setDBStrategy(new PropertyStrategy());
        return context.load();
    }

    public void saveCategories(ArrayList<String> categoryElements) {
        context.setDBStrategy(new CategoryText());
        context.save(categoryElements);
    }


    public void saveQuestions(ArrayList<String> questionElements) {
        context.setDBStrategy(new QuestionText());
        context.save(questionElements);
    }
}
