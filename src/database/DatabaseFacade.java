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
        ArrayList<String> vraagelementen = context.load();
        for (String x: vraagelementen){
            System.out.println(x);
        }
        return null;
    }
}
