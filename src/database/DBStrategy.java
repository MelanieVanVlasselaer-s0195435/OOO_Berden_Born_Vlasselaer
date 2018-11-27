package database;


import java.util.ArrayList;

public interface DBStrategy {
    public ArrayList load();
    public void save();

    //abstract methods
}
