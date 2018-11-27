package database;

import java.util.ArrayList;

public abstract class TxtDBStrategy implements DBStrategy {

    public abstract ArrayList load();

    public abstract void save();
}
