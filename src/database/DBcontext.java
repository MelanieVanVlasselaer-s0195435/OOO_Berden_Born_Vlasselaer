package database;

import java.util.ArrayList;
import java.util.List;

public class DBcontext {
    private DBStrategy strat;

    public static List getTestSources() {
        List<String> lijst = new ArrayList<>();
        for (TestSourceEnum source :TestSourceEnum.values()) {
            lijst.add(source.toString());
        }
        return lijst;
    }

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

    public void save(ArrayList elementen) {
        strat.save(elementen);
    }

}
