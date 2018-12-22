package database;

public class TestSourceFactory {

    public DBStrategy createStrategy(String testSource){
        TestSourceEnum sourceEnum = TestSourceEnum.valueOf(testSource);
        String klasseNaam = sourceEnum.getClassName();
        DBStrategy strategy = null;
        try {
            Class dbClass = Class.forName(klasseNaam);
            Object dbObject = dbClass.newInstance();
            strategy = (DBStrategy) dbObject;
        }
        catch (Exception e){
        }

        return strategy;
    }
}