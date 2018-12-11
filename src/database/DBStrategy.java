package database;


import java.util.ArrayList;

public interface DBStrategy {
    ArrayList load();
    void save(ArrayList<String> categorieElementen);

    //abstract methods
}
