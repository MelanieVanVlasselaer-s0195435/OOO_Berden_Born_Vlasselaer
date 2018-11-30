package database;

import model.Category;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class CategoryText extends TxtDBStrategy {
    File categoryFile;

    ArrayList<String> categorieElementen = new ArrayList<>();

    public CategoryText() {
        //voor mac en windows gebruiker in de groep hun verschillende pathnames te ondersteunen -FB
       categoryFile  = new File("testDatabase\\categoryList.txt");

       if (categoryFile.length() == 0) {
           categoryFile  = new File("testDatabase/categoryList.txt");
       }

    }

    //abstracte klassen implementeren

    public ArrayList<String> load () {

        try {
            Scanner scannerFile = new Scanner(categoryFile);
            while (scannerFile.hasNextLine()) {
                Scanner scannerLijn = new Scanner(scannerFile.nextLine());
                scannerLijn.useDelimiter("/");
                String name = scannerLijn.next();
                categorieElementen.add(name);
                String description = scannerLijn.next();
                categorieElementen.add(description);
                String hoofdcategorie = scannerLijn.next();
                categorieElementen.add(hoofdcategorie);
            }
        } catch (FileNotFoundException ex) {
            throw new RuntimeException("Fout bij het inlezen", ex);
        }
        return categorieElementen;
    }

    public void save () {

    }


}
