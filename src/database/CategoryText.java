package database;

import model.Category;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class CategoryText extends TxtDBStrategy {
    File categoryFile;

    ArrayList<Category> categories = new ArrayList<>();

    public CategoryText() {
       categoryFile  = new File("testDatabase\\categoryList.txt");
       if (categoryFile.length() == 0) {
           categoryFile  = new File("testDatabase/categoryList.txt");
       }
    }

    //abstracte klassen implementeren

    public ArrayList<Category> load () {

        try {
            Scanner scannerFile = new Scanner(categoryFile);
            while (scannerFile.hasNextLine()) {
                Scanner scannerLijn = new Scanner(scannerFile.nextLine());
                scannerLijn.useDelimiter("/");
                String name = scannerLijn.next();
                String description = scannerLijn.next();
                Category category = new Category(name, description);
                categories.add(category);
            }
        } catch (FileNotFoundException ex) {
            throw new RuntimeException("Fout bij het inlezen", ex);
        }
        return categories;
    }

    public void save () {

    }


}
