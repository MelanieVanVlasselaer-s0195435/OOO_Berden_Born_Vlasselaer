package database;

import java.util.ArrayList;

public class DatabaseFacade {
    DBcontext context = new DBcontext();

    public DatabaseFacade(){
        context.setDBStrategy(new CategoryText());
    }

    public ArrayList<String> loadCategorieElementen() {
        return context.load();
    }
}
