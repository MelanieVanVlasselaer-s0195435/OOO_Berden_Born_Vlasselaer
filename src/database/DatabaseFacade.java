package database;

import java.util.ArrayList;
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
    }

    public void saveTest(ArrayList elementen){
        context.setDBStrategy(currentsource);
        context.save(elementen);
    }


    public ArrayList<String> loadEvalutionStrategy() {
        context.setDBStrategy(new PropertyStrategy());
        return context.load();
    }

    public void saveEvalutionStrategy(String evaluationStrategy) {
        context.setDBStrategy(new PropertyStrategy());
        ArrayList<String> evaluationStrategies = new ArrayList<>();
        evaluationStrategies.add(0,evaluationStrategy);
        String source = (String)context.load().get(1);
        evaluationStrategies.add(1,source);
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
        context.setDBStrategy(new PropertyStrategy());
        ArrayList<String> evaluationStrategies = new ArrayList<>();
        String eval = (String)context.load().get(0);
        evaluationStrategies.add(0,eval);
        evaluationStrategies.add(1,source);
        context.save(evaluationStrategies);
    }

    public void setSource() {
        context.setDBStrategy(new PropertyStrategy());
        String from = (String) context.load().get(1);
        currentsource = factory.createStrategy(from);
    }
}
