package database;

import java.util.ArrayList;

public class DBcontext {
    private int strategy;
    private int keuze;
    private DBStrategy strat;

    public void setDBStrategy(DBStrategy x) {
        if (x instanceof DBStrategy) {
            strat = x;
        }
    }

    public ArrayList load() {
        return strat.load();
    }

    public void save() {
        strat.save();
    }

}
