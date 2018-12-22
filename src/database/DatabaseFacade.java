package database;

import database.TXT.CategoryText;
import database.TXT.QuestionText;
import database.TXT.TxtDBStrategy;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class DatabaseFacade {
    DBcontext context = new DBcontext();
    TestSourceFactory factory = new TestSourceFactory();
    DBStrategy currentsource;

    public DatabaseFacade(){
    }


    public ArrayList<ArrayList<String>> loadTest(){
        context.setDBStrategy(currentsource);
        return context.load();
/*
        NOG BETER
        context.setDBStrategy();
        return context.load();
*/
    }

    public void saveTest(ArrayList elementen){
        context.setDBStrategy(currentsource);
        context.save(elementen);
/*
        NOG BETER
        context.setDBStrategy();
        return context.save(elementen);
*/
    }

    //einde voorbeeld

    /*
    OOK DEZE MOETEN VERVANGEN WORDEN -TB
    public void saveCategories(ArrayList<String> categoryElements) {
        context.setDBStrategy(new CategoryText());
        context.save(categoryElements);
    }

    public void saveQuestions(ArrayList<String> questionElements) {
        context.setDBStrategy(new QuestionText());
        context.save(questionElements);
    }


    public ArrayList<String> loadCategorieElementen() {
        context.setDBStrategy(new CategoryText());
        return context.load();
    }

    public ArrayList<String> loadQuestionElementen(){
        context.setDBStrategy(new QuestionText());
        return context.load();
    }
     */

    public ArrayList<String> loadEvalutionStrategy() {
        context.setDBStrategy(new PropertyStrategy());
        return context.load();
    }

    public void saveEvalutionStrategy(String evaluationStrategy) {
        context.setDBStrategy(new PropertyStrategy());
        ArrayList<String> evaluationStrategies = new ArrayList<>();
        evaluationStrategies.add(evaluationStrategy);
        context.save(evaluationStrategies);
    }




    public void saveScore(ArrayList<String> score) {
        context.setDBStrategy(new ScoreStrategy());
        context.save(score);
    }

    public ArrayList<String> loadPreviousScore() {
        context.setDBStrategy(new ScoreStrategy());
        return context.load();
    }


    public List getTestSources() {
        return DBcontext.getTestSources();
    }

    public void saveTestSource(String source) {
        context.setDBStrategy(new SourcePropertyStrategy());
        ArrayList<String> evaluationStrategies = new ArrayList<>();
        evaluationStrategies.add(source);
        context.save(evaluationStrategies);
    }

    public void setSource() {
        context.setDBStrategy(new SourcePropertyStrategy());
        String from = (String) context.load().get(0);
        currentsource = factory.createStrategy(from);
    }
}
