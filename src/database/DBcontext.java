package database;

import java.util.ArrayList;

public class DBcontext {
    private DBStrategy strat;

    public DBStrategy getDBStrategy(){
        return strat;
    }

    public void setDBStrategy(DBStrategy x) {
        if (x instanceof DBStrategy) {
            strat = x;
        }
    }

    public ArrayList load() {
        return strat.load();
    }

    public void save(ArrayList<String> categorieElementen) {
        strat.save(categorieElementen);
    }

}
