package database;


import java.util.ArrayList;

public interface DBStrategy {
    ArrayList load();
    void save(ArrayList elementen);

    //abstract methods
}
