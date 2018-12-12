package database;

import java.util.ArrayList;

public abstract class TxtDBStrategy implements DBStrategy {

    //in voorbeeld in les plaatst Fox de scanner /scannerfile etc in deze methoden, wij een niveau lager
    //hij plaatst elk gescant object in een arraylist van objecten
    //=> hij gaat uiteindelijk in het niveau van CatagoryTxt & VraagTXT de elementen omzetten naar vragen/categoriën => niet MVC?
    //-TB
    public abstract ArrayList load();

    public abstract void save(ArrayList<String> elementen);
}
